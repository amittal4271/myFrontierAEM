<!DOCTYPE html>
<html lang="en">
     <head>
          <?php include(dirname(__FILE__).'/snippets/header-files-include.php');?>
     </head>

     <body>

          <?php include(dirname(__FILE__).'/snippets/header.php');?>

          <main class="main-content-container help-pages content-page">

               <section id="breadcrumbs">
                    <div class="container">
                         <ol class="breadcrumb">
                              <li><a href="#">Home</a></li>
                              <li class="active">Testing</li>
                         </ol>
                    </div>
               </section>


               <section class="content-standard">
                    <div class="container">
                         <div class="submit-holder checkout-flow">
                              <button id="test-ajax-button" data-textoriginal="Test Button" class="btn btn-light-green btn-larger btn-cart-checkout">Test Button</button>
                         </div>
                    </div>
               </section>

               
               
          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>


          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>

          <script>
               $(document).ready(function() {
                    
                    // test re-enable button click after 4 seconds

                    $(document).on( "click", "#test-ajax-button", function(e) {
                         var $this = $(this);
                         setTimeout(function(){ 
                              enableAjaxFormButton($this);
                         }, 4000);
                    });

                    
               });
          </script>
          
     </body>
</html>