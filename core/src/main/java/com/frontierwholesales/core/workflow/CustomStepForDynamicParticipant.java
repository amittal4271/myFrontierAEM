package com.frontierwholesales.core.workflow;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.HistoryItem;
import com.adobe.granite.workflow.exec.ParticipantStepChooser;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.metadata.MetaDataMap;

import java.util.List;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
@Service
@Properties({
		@org.apache.felix.scr.annotations.Property(name = "service.description", value = {
				"Sample Implementation of dynamic participant chooser." }),
		@org.apache.felix.scr.annotations.Property(name = "chooser.label", value = {
				"Frontier Workflow Participant Chooser" }) })
public class CustomStepForDynamicParticipant implements ParticipantStepChooser {
	private static final Logger logger = LoggerFactory.getLogger(CustomStepForDynamicParticipant.class);

	public String getParticipant(WorkItem workitem, WorkflowSession wfSession, MetaDataMap metaDataMap)
			throws WorkflowException {
		String participant = "admin";
		Workflow workflow = workitem.getWorkflow();
		String initiator = workflow.getInitiator();
		List<HistoryItem> wfHistory = wfSession.getHistory(workflow);
		if (!wfHistory.isEmpty()) {
			participant = initiator;
		} else {
			participant = "admin";
		}
		return participant;
	}
}