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
                              <li class="active">Reset Password</li>
                         </ol>
                    </div>
               </section>
               <section class="login-section">
                    <div class="container">
                         
                         <div class="return-customer-section">
                              <h2 class="heading-login-page">Reset your password</h2>
                              
                              <p>Enter your email address and new password.</p>

                              <form id="reset-password-form" class="form-section-start">
                                   <div class="form-group">
                                        <label for="id_username">Email:</label> 
                                        <input id="id_email" class="form-control" maxlength="254" name="email" type="email">
                                   </div>
                                   <div class="form-group">
                                        <label for="id_new_password">New Password:</label> 
                                        <input id="id_password" class="form-control" maxlength="254" name="new_password" type="text">
                                   </div>
                                   <div class="form-group">
                                        <label for="id_confirm_new_password">Confirm New Password:</label> 
                                        <input id="id_confirm_password" class="form-control" maxlength="254" name="confirm_new_password" type="text">
                                   </div>
                                   <div class="form-group button-holder">
                                        <input type="submit" class="btn btn-light-green" id="btn-login" value="Send">
                                   </div>
                                   
                              </form>
                              
                         </div>
                    </div>
               </section>
          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>
     </body>
</html>
