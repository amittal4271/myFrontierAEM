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

                         <section class="account-main-holder">
                              
                              <div class="account-heading-holder table-below">
                                   <h2 class="account-heading">Pending Invites</h2>
                                   <a href="#" class="btn btn-white-green btn-account-heading btn-account-form">Invite New Users</a>
                              </div>

                              <div class="account-information-holder">
                                   <table class="simple-responsive">
                                        <thead>
                                             <tr>
                                                  <th scope="col">First</th>
                                                  <th scope="col">Last</th>
                                                  <th scope="col">Email</th>
                                                  <th scope="col">Status</th>
                                                  <th scope="col">Actions</th>
                                             </tr>
                                        </thead>
                                        <tbody>
                                             <tr>
                                                  <td scope="row" data-label="First">Joe</td>
                                                  <td data-label="Last">Guerrette</td>
                                                  <td data-label="Email">jguerrette@ztech.io</td>
                                                  <td data-label="Status">Invited</td>
                                                  <td data-label="Actions">
                                                       <a href="#" class="btn btn-only-green-icon" title="Remove">
                                                            <span class="address-link-text visuallyhidden">Cancel</span>
                                                            <span class="glyphicon glyphicon-remove"></span>
                                                       </a>
                                                  </td>
                                             </tr>
                                             <tr>
                                                  <td scope="row" data-label="First">Justin</td>
                                                  <td data-label="Last">Prahst</td>
                                                  <td data-label="Email">jprahst32@gmail.com</td>
                                                  <td data-label="Status">Invited</td>
                                                  <td data-label="Actions">
                                                       <a href="#" class="btn btn-only-green-icon" title="Remove">
                                                            <span class="address-link-text visuallyhidden">Cancel</span>
                                                            <span class="glyphicon glyphicon-remove"></span>
                                                       </a>
                                                  </td>
                                             </tr>
                                        </tbody>
                                   </table>
                              </div>

                              <div class="account-heading-holder table-below below-section">
                                   <h2 class="account-heading">My Club Members</h2>
                              </div>
                              <div class="account-information-holder">
                                   <table class="simple-responsive">
                                        <thead>
                                             <tr>
                                                  <th scope="col">First</th>
                                                  <th scope="col">Last</th>
                                                  <th class="members-email-th" scope="col">Email</th>
                                                  <th scope="col">User Role</th>
                                                  <th scope="col">Status</th>
                                                  <th scope="col">Actions</th>
                                             </tr>
                                        </thead>
                                        <tbody>
                                             <tr>
                                                  <td scope="row" data-label="First">Britt</td>
                                                  <td data-label="Last">Miller</td>
                                                  <td class="members-email-td" data-label="Email">Brittany.Miller@auracacia.net</td>
                                                  <td data-label="Role">Owner</td>
                                                  <td data-label="Status">Active</td>
                                                  <td data-label="Actions">
                                                       <a href="#" class="btn btn-only-green-icon" title="Remove">
                                                            <span class="address-link-text visuallyhidden">Cancel</span>
                                                            <span class="glyphicon glyphicon-remove"></span>
                                                       </a>
                                                       <button type="button" class="btn btn-green-edit-icon float-right" title="Edit">
                                                            <span class="address-link-text">Edit</span>
                                                            <span class="glyphicon glyphicon-menu-down"></span>
                                                       </button>
                                                  </td>
                                             </tr>
                                             <tr>
                                                  <td scope="row" data-label="First">Britt</td>
                                                  <td data-label="Last">Miller</td>
                                                  <td class="members-email-td" data-label="Email">Brittany.Miller@auracacia.net</td>
                                                  <td data-label="Role">Owner</td>
                                                  <td data-label="Status">Active</td>
                                                  <td data-label="Actions">
                                                       <a href="#" class="btn btn-only-green-icon" title="Remove">
                                                            <span class="address-link-text visuallyhidden">Cancel</span>
                                                            <span class="glyphicon glyphicon-remove"></span>
                                                       </a>
                                                       <button type="button" class="btn btn-green-edit-icon float-right" title="Edit">
                                                            <span class="address-link-text">Edit</span>
                                                            <span class="glyphicon glyphicon-menu-down"></span>
                                                       </button>
                                                  </td>
                                             </tr>
                                        </tbody>
                                   </table>
                              </div>


                              <div class="account-heading-holder below-section">
                                   <h2 class="account-heading">Invite New Users</h2>
                              </div>
                              <div class="account-information-holder">
                                   <form id="form-invite-new-users">
                                        <fieldset class="account-fieldset-third-holder">
                                             <div class="form-group width-half first">
                                                  <label for="id_name">Name</label>
                                                  <input id="id_name" name="name" class="form-control" placeholder="Name" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_email">Email</label>
                                                  <input id="id_email" name="email" class="form-control" placeholder="Email" type="email">
                                             </div>
                                             <div class="form-group button-holder">
                                                  <input type="submit" class="btn btn-white-green btn-account-form" value="Invite">
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
