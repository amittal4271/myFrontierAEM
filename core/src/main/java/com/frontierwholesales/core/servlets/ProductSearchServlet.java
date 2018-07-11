package com.frontierwholesales.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.QueryBuilder;
import com.day.cq.search.Query;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.search.PredicateGroup;
import com.google.gson.stream.JsonWriter;

@SlingServlet(paths = "/bin/search", methods = "GET")
public class ProductSearchServlet extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 932570491641217502L;
	
	private static final Logger logger = LoggerFactory.getLogger( ProductSearchServlet.class );

	private static final String DEFAULT_PRODUCTS_PATH = "/etc/commerce/products";
	
	private static final String QUERY_PARAM = "q";
	private static final String LIMIT_PARAM = "limit";
	private static final String OFFSET_PARAM = "offset";
	
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	@Reference 
	private QueryBuilder queryBuilder;
	
	// TODO: make this configurable 
	private String productsPath = DEFAULT_PRODUCTS_PATH;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String searchParam = getRequestParameter(request, QUERY_PARAM);
		long offset = getOffsetRequestParameter(request);
		long limit = getLimitRequestParameter(request);
		
		long startTime = System.currentTimeMillis(); 
		logger.debug("Processing search request: query={}; offset={}; limit={}", new Object[] {searchParam, offset, limit});
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		JsonWriter writer = new JsonWriter(response.getWriter());
		
		try {
			
			if(searchParam != null && searchParam.length()>0) {
				// TODO: Assuming for now that resolver associated with request will have direct access to product data in repository.
				// The project however should define some service users needed for specific use cases.
				ResourceResolver resolver = request.getResourceResolver();
				
				Session session = resolver.adaptTo( Session.class );
				search(writer, session, searchParam, offset, limit);
			} else {
				writer.beginArray();
				writer.endArray();
			}
		
		} finally {
			if(writer != null) {
				writer.flush();
				writer.close();
			}
			
			if(logger.isDebugEnabled()) {
				logger.debug("Search request completed in {} ms", System.currentTimeMillis()-startTime);
			}
		}
	}
	
	private void search(JsonWriter writer, Session session, String text, long offset, long limit) throws ServletException, IOException {
		writer.beginArray();
		
		com.day.cq.search.Query query = createQuery(text, session);
		query.setStart(offset);
		if(limit > offset) {
			query.setHitsPerPage(limit);
		}
		
		try {
			SearchResult result = query.getResult();
			for(final Hit hit : result.getHits()) {
				Node nextNode = hit.getNode();
				writeProduct(writer, nextNode);
			}
			
			logger.debug("Executed query '{}' in {}ms.", new Object[] {result.getQueryStatement(), result.getExecutionTimeMillis()});
		} catch (RepositoryException e) {
			logger.error("Unable to execute search query with parameter '{}'", new Object[] {text}, e);
			throw new ServletException("Unable to process request.");
		} finally {
			writer.endArray();
		}
	}
	
	private void writeProduct(JsonWriter writer, Node productNode) throws IOException {
		String productPath = null;
		
		writer.beginObject();
		try {
			productPath = productNode.getPath();
			logger.debug("Adding product {} to search results.", productPath);
			writer.name("id").value(getProperty(productNode, "identifier"));
			writer.name("baseSku").value(getProperty(productNode, "baseSku"));
			writer.name("name").value(getProperty(productNode, "jcr:title"));
			writer.name("summary").value(getProperty(productNode, "summary"));
			writer.name("description").value(getProperty(productNode, "jcr:description"));
			writer.name("image").value(getProductImage(productNode));
		} catch (RepositoryException e) {
			logger.error("Unable to add product {} to response object.", new Object[] {productPath});
		} finally {
			writer.endObject();
		}
	}
	
	private Query createQuery(String text, Session session) {
		final Map<String, String> map = new HashMap<String, String>();
		map.put("path", productsPath);
		map.put("property", "cq:commerceType");
		map.put("property.value", "product");
		map.put("fulltext", text + "*");
		map.put("orderby", "@jcr:score");
		map.put("sort", "desc");
		return queryBuilder.createQuery(PredicateGroup.create(map), session);
	}
	
	private long getOffsetRequestParameter(SlingHttpServletRequest request) {
		return getNumRequestParameter(request, OFFSET_PARAM);
	}
	
	private long getLimitRequestParameter(SlingHttpServletRequest request) {
		return getNumRequestParameter(request, LIMIT_PARAM);
	}
	
	private long getNumRequestParameter(SlingHttpServletRequest request, String name) {
		long num = 0;
		String p = getRequestParameter(request, name);
		if(p != null) {
			try {
				num = Long.parseLong(p);
			} catch(NumberFormatException nfe) {}
		}
		return num;
	}
	
	private String getRequestParameter(SlingHttpServletRequest request, String name) {
		String param = request.getParameter(name) == null ? null : request.getParameter(name).trim();
		return param;
	}
	
	private String getProductImage(Node productNode) throws RepositoryException {
		if(productNode.hasNode("image")) {
			Node imageNode = productNode.getNode("image");
			return getProperty(imageNode, "fileReference");
		}
		
		return "";
	}
	
	private String getProperty(Node node, String name) throws RepositoryException {
		if(node.hasProperty(name)) {
			return node.getProperty(name).getString();
		}
		
		return "";
	}

}
