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
                              <li class="active">Forgot Password</li>
                         </ol>
                    </div>
               </section>
               <section class="login-section">
                    <div class="container">
                         
                         <div class="return-customer-section">
                              <h2 class="heading-login-page">Forgot your password?</h2>
                              
                              <p>Enter your email address and we'll send you a password reset email.</p>

                              <form id="forgot-password-form" class="form-section-start">
                                   <div class="form-group">
                                        <label for="id_username">Email:</label> 
                                        <input id="id_email" class="form-control" maxlength="254" name="email" type="email">
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
