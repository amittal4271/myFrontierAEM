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
                              <li class="active">Login</li>
                         </ol>
                    </div>
               </section>
               <section class="login-section">
                    <div class="container">
                         
                         <div class="return-customer-section">
                              <h2 class="heading-login-page">Login</h2>
                              
                              <p class="standard-text">Welcome back! Please sign in using your email address and password below.</p>

                              <form id="login-form" class="form-section-start">
                                   <div class="form-group">
                                        <label for="id_username" class="hidden">Email address (or user name):</label>
                                        <input id="id_username" class="form-control" maxlength="254" name="username" type="text" placeholder="Email address (or user name)">
                                   </div>
                                   
                                   <div class="form-group">
                                        <label for="id_password" class="hidden">Password:</label>
                                        <input id="id_password" class="form-control" name="password" type="password" placeholder="Password">
                                   </div>
                                   <div class="form-group button-holder">
                                        <input type="submit" class="btn btn-light-green btn-account-form" id="btn-login" value="Sign In">
                                   </div>
                                   <small class="login-forgot-password small-form-element">
                                        <a href="/account/password-reset/" class="login-password-link">Forgot password?</a>
                                   </small>
                                   
                              </form>
                              
                         </div>
                         
                         <div class="new-customer-section">
                              <h2 class="heading-login-page">New Customer</h2>
                              <p class="standard-text">Create an account to check out faster in the future and receive emails about your orders, new products, events and special offers!</p>
                              <div class="form-group">
                                   <a href="#" class="btn btn-light-green btn-account-form">Create Account</a>
                              </div>
                         </div>
                    </div>
               </section>
          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>
     </body>
</html>
