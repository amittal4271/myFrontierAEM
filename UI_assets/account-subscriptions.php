<!DOCTYPE html>
<html lang="en">
     <head>
          <?php include(dirname(__FILE__).'/snippets/header-files-include.php');?>
     </head>

     <body>

          <?php include(dirname(__FILE__).'/snippets/header.php');?>

          <main class="main-content-container account-section-max-1200">

               <section class="account-page-holder">
                    <div class="container">
                         <?php include(dirname(__FILE__).'/snippets/account-nav.php');?>

                         <section class="account-main-holder  clearfix">
                              <div class="account-heading-holder">
                                   <h2 class="account-heading">Subscriptions</h2>
                              </div>
                              <div class="account-information-holder">
                                   <form class="account-form" id="form-contact-information">
                                        <fieldset class="account-fieldset-third-holder">
                                             <div class="checkbox account-email-subscription">
                                                  <label for="subscription_email">
                                                       <input id="subscription_email" name="subscription_email" type="checkbox">
                                                       <span class="label-text">E-mail - Email</span>
                                                  </label>
                                             </div>

                                             <div class="checkbox account-email-subscription">
                                                  <label for="subscription_print">
                                                       <input id="subscription_print" name="subscription_print" type="checkbox">
                                                       <span class="label-text">Catalog - Print</span>
                                                  </label>
                                             </div>
                                             
                                             <div class="form-group button-holder">
                                                  <input type="submit" class="btn btn-white-green btn-account-form" value="Update">
                                             </div>
                                        </fieldset>
                                   </form>
                              </div>
                              
                         </section>
                    </div>
               </section>
          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>
     </body>
</html>
