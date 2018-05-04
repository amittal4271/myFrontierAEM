<!DOCTYPE html>
<html lang="en">
     <head>
          <?php include(dirname(__FILE__).'/snippets/header-files-include.php');?>
     </head>

     <body class="home-page">

          <?php include(dirname(__FILE__).'/snippets/header.php');?>

          <main id="test-home" class="main-content-container">
               <section id="scroller-content">
                    <div class="container">
                         <div class="row">
                              <div id="homepage-scroller" class="col-md-8">

                                   <div class="swiper-container">
                                        <div class="swiper-wrapper">
                                             <div class="swiper-slide">
                                                  <img alt="Test Alt Text for Screen readers" src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/mantles/clif-b/clif-bar-march-108-5be4/41446fb9d476fd4db9ec776a41ab3301.jpg"/>
                                             </div>
                                             <div class="swiper-slide">
                                                  <img alt="Test Alt Text for Screen readers" src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/mantles/giovan/giovanni-march-110-53d0/11879a02688459a9aaf1477aeb7f9dae.jpg"/>
                                             </div>
                                             <div class="swiper-slide">
                                                  <img alt="Test Alt Text for Screen readers" src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/mantles/mantle/mantle-7-eco-nuts-march-143-84c2/388de5bb269b868bd83caafb1ab4abb1.jpg"/>
                                             </div>
                                        </div>

                                        <div class="swiper-pagination"></div>

                                        <div class="swiper-button-prev"></div>
                                        <div class="swiper-button-next"></div>
                                   </div>

                              </div>
                              <div id="homepage-right-side" class="col-md-4">
                                   <div class="each-side-image background-image">
                                        <a href="#" style="background-image: url('http://bcforestdiscoverycentre.com/wp-content/uploads/2012/06/easter-bunny.jpg');">
                                             <span class="right-image-heading">Fill Your<br/> Easter Basket</span>
                                             <span class="btn btn-call-to-action-right-side btn-purple">Shop Now</span>
                                        </a>
                                   </div>
                                   <div class="each-side-image background-image">
                                        <a href="#" style="background-image: url('https://www.sciencenewsforstudents.org/sites/default/files/2017/05/main/blogposts/860_main_library_bacteria.png');">
                                             <span class="right-image-heading white">Brand of the Month</span>
                                             <span class="btn btn-call-to-action-right-side btn-blue">Read More</span>
                                        </a>
                                   </div>
                              </div>
                         </div>
                    </div>
               </section>
               <?php include(dirname(__FILE__).'/snippets/featured-brands.php');?>
               <?php include(dirname(__FILE__).'/snippets/new-products-specials.php');?>
               <?php include(dirname(__FILE__).'/snippets/blog-subscribe.php');?>
          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>


          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>
     </body>
</html>
