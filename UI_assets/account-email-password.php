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

                         <section class="account-main-holder clearfix">
                              <div class="account-heading-holder">
                                   <h2 class="account-heading">Email and Password</h2>
                              </div>
                              <div class="account-information-holder">
                                   <form class="account-form" id="form-contact-information">
                                        <fieldset class="account-fieldset-third-holder">
                                             <div class="form-group width-half first">
                                                  <label for="id_email">Email address</label>
                                                  <input id="id_email" maxlength="254" class="form-control" name="email" type="email" value="justin@enabl.net">
                                             </div>
                                             
                                             <div class="email-password-clear"></div>
                                             
                                             <div class="form-group width-half first">
                                                  <label for="id_password">Update Password</label>
                                                  <input id="id_password" class="form-control" name="password" type="password">
                                                  <small class="small-form-element">Make sure to use a strong password, 8 character minimum!</small>
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_password_confirm">Confirm Password</label>
                                                  <input id="id_password_confirm" class="form-control" name="password_confirm" type="password">
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
