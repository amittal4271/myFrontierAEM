<!DOCTYPE html>
<html lang="en">
     <head>
          <?php include(dirname(__FILE__).'/snippets/header-files-include.php');?>
     </head>

     <body>

          <?php include(dirname(__FILE__).'/snippets/header.php');?>

          <main class="main-content-container account-section-max-1200">
               <section id="breadcrumbs">
                    <div class="container">
                         <ol class="breadcrumb">
                              <li><a href="#">Home</a></li>
                              <li><a href="#">Shop</a></li>
                              <li><a href="#">Grocery</a></li>
                              <li class="active">Beverages</li>
                         </ol>
                    </div>
               </section>
               <article class="product-list-page">
                    <div class="container">
                         <aside id="plp-search-left-nav-filters">
                              <div class="each-filter-section categories">
                                   <h2 class="filter-heading">Categories</h2>
                                   <ul class="each-filters-list">
                                        <li class="filter-item">
                                             <a href="#" class="filter-link">Aloe Vera</a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link">Coffee</a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link">Hot Cocoa</a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link">Powdered Drink Mixes</a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link">Tea</a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link">Flavors and Syrups</a>
                                        </li>
                                        <li class="filter-item filter-item-expand">
                                             <button type="button" class="btn btn-filters-expand">
                                                  <span class="filters-expand__more">View More</span>
                                             </button>
                                        </li>
                                   </ul>
                              </div>
                         </aside>
                         <section id="plp-search-product-grid">
                         </section>
                    </div>
               </article>
          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>
     </body>
</html>
