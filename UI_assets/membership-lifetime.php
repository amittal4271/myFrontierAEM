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
                              <li><a href="#">Membership</a></li>
                              <li class="active">Lifetime Membership</li>
                         </ol>
                    </div>
               </section>

               <section id="membership-holder">
                    <div class="container">
                         <div class="lifetime-leaf"></div>
                         <h1 class="plan-heading">Lifetime Membership</h1>
                         <div class="membership-form-section">
                              <form id="lifetime-membership-form">

                                   <fieldset class="each-form-section">
                                        <legend class="each-form-title">
                                             <span class="title-text">Type of Business</span>
                                        </legend>
                                        <div class="each-form-information">
                                             <div class="checkbox">
                                                  <label for="id_account-buying_club">
                                                       <input id="id_account-buying_club" name="account-buying_club" type="checkbox">
                                                       <span class="label-text">Buying Club</span>
                                                  </label>
                                             </div>
                                             <div id="buying-club-additional-invites-holder">
                                                  <small class="small-form-element">You must invite 4 people to join the buying club</small>

                                                  <input type="hidden" value="4" id="buyer-member-count-input"/>

                                                  <div id="buyer-club-group-holder">
                                                       <div id="invite-group-section-1" class="each-invite-group-section">
                                                            <div class="form-group name-group width-half first">
                                                                 <label for="id_invite-1-name">Name</label>
                                                                 <input id="id_invite-1-name" name="invite-1-name" placeholder="Name" type="text" class="form-control">
                                                            </div>
                                                            <div class="form-group email-group width-half">
                                                                 <label for="id_invite-1-email">Email</label>
                                                                 <input id="id_invite-1-email" name="id_invite-1-email" placeholder="Email" type="text" class="form-control">
                                                            </div>
                                                       </div>

                                                       <div id="invite-group-section-2" class="each-invite-group-section">
                                                            <div class="form-group name-group width-half first">
                                                                 <label for="id_invite-2-name">Name</label>
                                                                 <input id="id_invite-2-name" name="invite-2-name" placeholder="Name" type="text" class="form-control">
                                                            </div>
                                                            <div class="form-group email-group width-half">
                                                                 <label for="id_invite-2-email">Email</label>
                                                                 <input id="id_invite-2-email" name="id_invite-2-email" placeholder="Email" type="text" class="form-control">
                                                            </div>
                                                       </div>

                                                       <div id="invite-group-section-3" class="each-invite-group-section">
                                                            <div class="form-group name-group width-half first">
                                                                 <label for="id_invite-3-name">Name</label>
                                                                 <input id="id_invite-3-name" name="invite-3-name" placeholder="Name" type="text" class="form-control">
                                                            </div>
                                                            <div class="form-group email-group width-half">
                                                                 <label for="id_invite-3-email">Email</label>
                                                                 <input id="id_invite-3-email" name="id_invite-3-email" placeholder="Email" type="text" class="form-control">
                                                            </div>
                                                       </div>

                                                       <div id="invite-group-section-4" class="each-invite-group-section">
                                                            <div class="form-group name-group width-half first">
                                                                 <label for="id_invite-4-name">Name</label>
                                                                 <input id="id_invite-4-name" name="invite-4-name" placeholder="Name" type="text" class="form-control">
                                                            </div>
                                                            <div class="form-group email-group width-half">
                                                                 <label for="id_invite-4-email">Email</label>
                                                                 <input id="id_invite-4-email" name="id_invite-4-email" placeholder="Email" type="text" class="form-control">
                                                            </div>
                                                       </div>
                                                  </div>

                                                  <div id="invite-group-actions">
                                                       <button type="button" class="btn btn-link green-link btn-add-another-buyer-member">
                                                            <span class="glyphicon glyphicon-plus"></span>Add Another Line
                                                       </button>
                                                  </div>
                                             </div>
                                             <hr class="line-separator after-buying-club">
                                             <div class="radio-checkbox-section-holder thirds">
                                                  <div class="radio">
                                                       <label for="id_account-business_type_0">
                                                            <input id="id_account-business_type_0" class="other-than-buying-club-input" name="account-business_type" type="radio" value="food-service">
                                                            <span class="label-text">Food Service</span>
                                                       </label>
                                                  </div>
                                                  <div class="radio">
                                                       <label for="id_account-business_type_1">
                                                            <input id="id_account-business_type_1" class="other-than-buying-club-input" name="account-business_type" type="radio" value="grocery">
                                                            <span class="label-text">Grocery</span>
                                                       </label>
                                                  </div>
                                                  <div class="radio">
                                                       <label for="id_account-business_type_2">
                                                            <input id="id_account-business_type_2" class="other-than-buying-club-input" name="account-business_type" type="radio" value="health">
                                                            <span class="label-text">Health Practitioner</span>
                                                       </label>
                                                  </div>


                                                  <div class="radio">
                                                       <label for="id_account-business_type_3">
                                                            <input id="id_account-business_type_3" class="other-than-buying-club-input" name="account-business_type" type="radio" value="massage">
                                                            <span class="label-text">Massage/Spa</span>
                                                       </label>
                                                  </div>
                                                  <div class="radio">
                                                       <label for="id_account-business_type_4">
                                                            <input id="id_account-business_type_4" class="other-than-buying-club-input" name="account-business_type" type="radio" value="natural-foods">
                                                            <span class="label-text">Natural Foods Retailer</span>
                                                       </label>
                                                  </div>
                                                  <div class="radio">
                                                       <label for="id_account-business_type_5">
                                                            <input id="id_account-business_type_5" class="other-than-buying-club-input" name="account-business_type" type="radio" value="other">
                                                            <span class="label-text">Other</span>
                                                       </label>
                                                  </div>

                                                  <div class="radio">
                                                       <label for="id_account-business_type_6">
                                                            <input id="id_account-business_type_6" class="other-than-buying-club-input" name="account-business_type" type="radio" value="retail">
                                                            <span class="label-text">Retail Business</span>
                                                       </label>
                                                  </div>
                                                  <div class="radio">
                                                       <label for="id_account-business_type_7">
                                                            <input id="id_account-business_type_7" class="other-than-buying-club-input" name="account-business_type" type="radio" value="web">
                                                            <span class="label-text">Web Retailer</span>
                                                       </label>
                                                  </div>
                                             </div>
                                             <hr class="line-separator after-radio-section">
                                             <div class="checkbox width-one-third">
                                                  <label for="id_account-online_only">
                                                       <input id="id_account-online_only" name="account-online_only" type="checkbox">
                                                       <span class="label-text">Online Only Store</span>
                                                  </label>
                                             </div>
                                             
                                        </div>
                                   </fieldset>


                                   <fieldset class="each-form-section">
                                        <legend class="each-form-title">
                                             <span class="title-text">Tax Payer Information</span>
                                        </legend>
                                        <div class="each-form-information">
                                             <div class="form-group width-half first">
                                                  <label for="id_mailing-name">Full Name</label>
                                                  <input id="id_mailing-name" name="mailing-name" type="text" class="form-control">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_account-company_name">Company or Buying Club Name</label>
                                                  <input id="id_account-company_name" name="account-company_name" type="text" class="form-control">
                                             </div>
                                             <div class="form-group">
                                                  <label for="id_account-taxpayer_id">Tax Payer ID# or Social Security Number</label>
                                                  <input id="id_account-taxpayer_id" name="account-taxpayer_id" type="text" class="form-control">
                                                  <small class="small-form-element">(TIN, EIN, SSN) When providing your Tax Payer ID/Social Security Number, please include the dashes. Ex: xxx-xx-xxxx</small>
                                             </div>
                                        </div>
                                   </fieldset>

                                   <fieldset class="each-form-section">
                                        <legend class="each-form-title">
                                             <span class="title-text">Tax Payer Mailing Address</span>
                                        </legend>
                                        <div class="each-form-information">
                                             <div class="form-group">
                                                  <label for="id_mailing-address">Address Line 1</label>
                                                  <input id="id_mailing-address" class="form-control" maxlength="150" name="mailing-address" placeholder="123 Example Street" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_mailing-address2">Address Line 2</label>
                                                  <input id="id_mailing-address2" class="form-control" maxlength="75" name="mailing-address2" placeholder="Suite 55C" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_mailing-city">City</label>
                                                  <input id="id_mailing-city" class="form-control" maxlength="70" name="mailing-city" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_mailing-locality">State</label>
                                                  <select id="id_mailing-locality" class="form-control" name="mailing-locality">
                                                       <option value="1">Alabama</option>
                                                       <option value="5">Alaska</option>
                                                       <option value="275">American Samoa</option>
                                                       <option value="6">Arizona</option>
                                                       <option value="7">Arkansas</option>
                                                       <option value="281">Armed Forces Americas</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_mailing-postal_code">Zip</label>
                                                  <input id="id_mailing-postal_code" class="form-control" name="mailing-postal_code" type="text">
                                             </div>
                                             <div class="form-group">
                                                  <label for="id_mailing-country">Country</label>
                                                  <select id="id_mailing-country" class="form-control" name="mailing-country">
                                                       <option value="840">United States of America</option>
                                                  </select>
                                             </div>
                                        </div>
                                   </fieldset>

                                   <fieldset class="each-form-section">
                                        <legend class="each-form-title">
                                             <span class="title-text">Billing Address</span>

                                             <p class="form-title-information">Note: Canadian orders must be phoned (800) 669-3275 or faxed (800) 717-4372 to our Customer Service Department, because we are unable to calculate shipping charges online.</p>

                                             <p class="form-title-information">Frontier Co-op does not currently sell to manufacturers of industrial suppliers.</p>

                                             <p class="form-title-information">Unless we have a valid signed sales tax exemption form covering the address to which an order is shipped, we charge applicable sales tax in California, Colorado, Illinois, Iowa, Texas, Virginia, and Washington. <a href="//dh6qdiyp93d9u.cloudfront.net/media/files/membership-sale-tax-uniform-exemption-19-d1f5.pdf" target="_blank" rel="noopener noreferrer">Sales Tax Uniform Exemption Form</a> 
                                             </p>
                                        </legend>
                                        <div class="each-form-information">
                                             <div class="checkbox">
                                                  <label for="id_shipping-shipping_same">
                                                       <input id="id_shipping-shipping_same" name="shipping-shipping_same" type="checkbox">
                                                       <span class="label-text">Same as Tax Payer Mailing Address</span>
                                                  </label>
                                             </div>
                                             <hr class="line-separator after-buying-club">
                                             <div class="form-group width-half first">
                                                  <label for="id_shipping-name">Full Name</label>
                                                  <input id="id_shipping-name" class="form-control" maxlength="150" name="shipping-name" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_shipping-company">Company</label>
                                                  <input id="id_shipping-company" class="form-control" maxlength="30" name="shipping-company" type="text">
                                             </div>
                                             <div class="form-group">
                                                  <label for="id_shipping-address">Address Line 1</label>
                                                  <input id="id_shipping-address" maxlength="150" class="form-control" name="shipping-address" placeholder="123 Example Street" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_shipping-address2">Address Line 2</label>
                                                  <input id="id_shipping-address2" class="form-control" maxlength="75" name="shipping-address2" placeholder="Suite 55C" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_shipping-city">City</label>
                                                  <input id="id_shipping-city" class="form-control" maxlength="70" name="shipping-city" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_shipping-locality">State</label>
                                                  <select id="id_shipping-locality" class="form-control" name="shipping-locality">
                                                       <option value="1">Alabama</option>
                                                       <option value="5">Alaska</option>
                                                       <option value="275">American Samoa</option>
                                                       <option value="6">Arizona</option>
                                                       <option value="7">Arkansas</option>
                                                       <option value="281">Armed Forces Americas</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_shipping-postal_code">Zip</label>
                                                  <input id="id_shipping-postal_code" class="form-control" name="shipping-postal_code" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_shipping-country">Country</label>
                                                  <select id="id_shipping-country" class="form-control" name="shipping-country">
                                                       <option value="840" selected="selected">United States of America</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_shipping-phone">Phone</label>
                                                  <input id="id_shipping-phone" class="form-control" name="shipping-phone" type="text">
                                             </div>
                                             <div class="form-group">
                                                  <div class="lifetime-membership-disclaimer">
                                                       Under penalties of perjury, I certify that:
                                                       <ol class="lifetime-membership-disclaimer-list">
                                                            <li>The number shown is my correct taxpayer identification number (or I am waiting for a number to be issued to me).</li>
                                                            <li>I am not subject to backup withholding because: (a) I am exempt from backup withholding, or (b) I have not been notified by the Internal Revenue Service that I am subject backup withholding as a result of a failure to report all interest or dividends, or (c) the IRS has notified me that I am no longer subject to breakup withholding.</li>
                                                       </ol>
                                                       
                                                       <p class="lifetime-membership-disclaimer-acknowledge">I acknowledge that by typing my name below, I am indicating my intent to sign the document and that this shall constitute my signature.</p>
                                                  </div>
                                             </div>
                                             <div class="form-group">
                                                  <label for="id_account-signature"><strong>Signature</strong></label>
                                                  <input id="id_account-signature" class="form-control" name="account-signature" type="text">
                                             </div>
                                             <div class="checkbox">
                                                  <label for="id_account-credit_terms">
                                                       <input id="id_account-credit_terms" name="account-credit_terms" type="checkbox">
                                                       <span class="label-text">Do you wish to be considered for credit terms? Approx. 30 business days for approval.</span>
                                                  </label>
                                             </div>
                                        </div>
                                   </fieldset>

                                   <fieldset class="each-form-section">
                                        <legend class="each-form-title">
                                             <span class="title-text">Create Online Account Access</span>
                                        </legend>
                                        <div class="each-form-information">
                                             <div class="form-group width-half first">
                                                  <label for="id_membership-name">Name</label>
                                                  <input id="id_membership-name" class="form-control" name="membership-name" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_membership-email">Email</label>
                                                  <input id="id_membership-email" class="form-control" name="membership-email" type="email">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_membership-password">Password</label>
                                                  <input id="id_membership-password" class="form-control" name="membership-password" type="password">
                                                  <small class="small-form-element">Make sure to use a strong password, 8 character minimum!</small>
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_membership-password_confirm">Confirm Password</label>
                                                  <input id="id_membership-password_confirm" class="form-control" name="membership-password_confirm" type="password">
                                             </div>
                                        </div>
                                   </fieldset>

                                   <fieldset class="each-form-section">
                                        <legend class="each-form-title">
                                             <span class="title-text">$10 Lifetime Membership Activation</span>
                                             <p class="form-title-information">Become a lifetime member/owner of Frontier Co-op for just $10. For this one-time refundable fee, you'll get membership benefits, including a share of the co-op's annual profits, a vote on co-op issues and exclusive member pricing.</p>
                                        </legend>
                                        <div class="each-form-information">
                                             <div class="form-group width-half first">
                                                  <label for="id_billing-name">Full Name</label>
                                                  <input id="id_billing-name" class="form-control" maxlength="70" name="billing-name" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_billing-company">Company</label>
                                                  <input id="id_billing-company" class="form-control" maxlength="30" name="billing-company" type="text">
                                             </div>
                                             <div class="form-group">
                                                  <label for="id_billing-address">Address Line 1</label>
                                                  <input id="id_billing-address" class="form-control" maxlength="150" name="billing-address" placeholder="123 Example Street" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_billing-address2">Address Line 2</label>
                                                  <input id="id_billing-address2" class="form-control" maxlength="75" name="billing-address2" placeholder="Suite 55C" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_billing-city">City</label>
                                                  <input id="id_billing-city" maxlength="70" class="form-control" name="billing-city" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_billing-locality">State</label>
                                                  <select id="id_billing-locality" class="form-control" name="billing-locality">
                                                       <option value="1">Alabama</option>
                                                       <option value="5">Alaska</option>
                                                       <option value="275">American Samoa</option>
                                                       <option value="6">Arizona</option>
                                                       <option value="7">Arkansas</option>
                                                       <option value="281">Armed Forces Americas</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half ">
                                                  <label for="id_billing-postal_code">Zip</label>
                                                  <input id="id_billing-postal_code" class="form-control" name="billing-postal_code" novalidate="novalidate" type="text">
                                             </div>
                                             <div class="form-group">
                                                  <label for="id_billing-country">Country</label>
                                                  <select id="id_billing-country" class="form-control" name="billing-country">
                                                       <option value="840" selected="selected">United States of America</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_billing-number">Credit Card Number</label>
                                                  <input id="id_billing-number" class="form-control" maxlength="20" name="billing-number" placeholder="**** **** **** ****" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_billing-cvv">CVV Code</label>
                                                  <input id="id_billing-cvv" class="form-control" maxlength="4" name="billing-cvv" placeholder="***" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_billing-exp_month">Expiration Month</label>
                                                  <select id="id_billing-exp_month" class="form-control" name="billing-exp_month">
                                                       <option value="1">01</option>
                                                       <option value="2">02</option>
                                                       <option value="3">03</option>
                                                       <option value="4">04</option>
                                                       <option value="5">05</option>
                                                       <option value="6">06</option>
                                                       <option value="7">07</option>
                                                       <option value="8">08</option>
                                                       <option value="9">09</option>
                                                       <option value="10">10</option>
                                                       <option value="11">11</option>
                                                       <option value="12">12</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_billing-exp_year">Expiration Year</label>
                                                  <select id="id_billing-exp_year" class="form-control" name="billing-exp_year">
                                                       <option value="2018">2018</option>
                                                       <option value="2019">2019</option>
                                                       <option value="2020">2020</option>
                                                       <option value="2021">2021</option>
                                                       <option value="2022">2022</option>
                                                       <option value="2023">2023</option>
                                                       <option value="2024">2024</option>
                                                       <option value="2025">2025</option>
                                                       <option value="2026">2026</option>
                                                       <option value="2027">2027</option>
                                                  </select>
                                             </div>
                                        </div>
                                   </fieldset>

                                   <div class="submit-holder">
                                        <button type="button" class="btn btn-light-green btn-account-form" id="btn-submit-lifetime-registration">Submit for Approval</button>
                                   </div>

                              </form>
                         </div>
                    </div>
               </section>
          </main>

          

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>

          <script>
               ;(function($) {

                    window.buyingClub = {
                         buyerMemberCountInput: $('#buyer-member-count-input'),
                         groupHolder: $('#buyer-club-group-holder'),
                         countMin: 4,
                         countMax: 100,
                         currentCount: 4,

                         addNewRowHtml: function () {
                              var $newRowHtml = '';

                              $newRowHtml += '<div id="invite-group-section-'+buyingClub.currentCount+'" class="each-invite-group-section">';
                                   $newRowHtml += '<div class="form-group name-group width-half first">';
                                        $newRowHtml += '<label for="id_invite-'+buyingClub.currentCount+'-name">Name</label>';
                                        $newRowHtml += '<input id="id_invite-'+buyingClub.currentCount+'-name" name="invite-'+buyingClub.currentCount+'-name" placeholder="Name" type="text" class="form-control">';
                                   $newRowHtml += '</div>';
                                   $newRowHtml += '<div class="form-group email-group width-half">';
                                        $newRowHtml += '<label for="id_invite-'+buyingClub.currentCount+'-email">Email</label>';
                                        $newRowHtml += '<input id="id_invite-'+buyingClub.currentCount+'-email" name="id_invite-'+buyingClub.currentCount+'-email" placeholder="Email" type="text" class="form-control">';
                                   $newRowHtml += '</div>';
                                   $newRowHtml += '<div class="form-group remove-buying-club-btn-holder">';
                                        $newRowHtml += '<button type="button" class="btn btn-link red-link btn-remove-buyer-member">';
                                             $newRowHtml += '<span class="glyphicon glyphicon-minus"></span>Remove Line Item';
                                        $newRowHtml += '</button>';
                                   $newRowHtml += '</div>';
                              $newRowHtml += '</div>';

                              buyingClub.groupHolder.append($newRowHtml);
                         },

                         removeRow: function ($btnEl) {
                              // find parent invite group holder
                              var $inviteGroupSectionRemoveHolder = $btnEl.parents('.each-invite-group-section');
                              // remove html
                              $inviteGroupSectionRemoveHolder.remove();
                              buyingClub.updateLineItemCountsOnRemove();
                         },

                         updateLineItemCountsOnRemove: function () {
                              // we need to update numbering based on line item number that is removed
                              if (buyingClub.currentCount > 4) {
                                   $('.each-invite-group-section').each(function (index, value) {
                                        var $this = $(this);
                                        var $nameGroup = $this.children('.name-group');
                                        var $emailGroup = $this.children('.email-group');
                                        console.log(index+1);
                                        // set index to match numbers starting at 1
                                        var $newIndex = index+1;
                                        // reset html that has numbers according to new index
                                        $this.attr('id', 'invite-group-section-'+$newIndex);

                                        // set new index values on labels and inputs for name and email
                                        var $nameLabel = $nameGroup.find('label');
                                        var $nameInput = $nameGroup.find('input');

                                        $nameLabel.attr('for', 'id_invite-'+$newIndex+'-name');
                                        $nameInput.attr('id', 'id_invite-'+$newIndex+'-name');
                                        $nameInput.attr('name', 'invite-'+$newIndex+'-name');

                                        var $emailLabel = $emailGroup.find('label');
                                        var $emailInput = $emailGroup.find('input');

                                        $emailLabel.attr('for', 'id_invite-'+$newIndex+'-email');
                                        $emailInput.attr('id', 'id_invite-'+$newIndex+'-email');
                                        $emailInput.attr('name', 'invite-'+$newIndex+'-email');
                                   });
                              }
                         }
                    };

               })(jQuery);
          
               $(document).ready(function() {

                    validation.registrationLifetimeForm('#lifetime-membership-form');

                    $(document).on( "click", "#btn-submit-lifetime-registration", function(e) {
                         e.preventDefault();

                         $validFlag = $("#lifetime-membership-form").valid();

                         if ($validFlag) {
                              console.log('valid form');
                              
                              alert('We can reference our ajax function here, maybe set up another namespace for ajax forms');
                         } else {
                              console.log('not valid');
                         }
                    });

                    $(document).on( "change", "#id_account-buying_club", function(e) {
                         console.log('buying club radio change');
                         var $this = $(this);

                         var $buyingClubInvitesHolder = $('#buying-club-additional-invites-holder');
                         
                         if ($buyingClubInvitesHolder.hasClass('show-invites-holder')) {
                              $buyingClubInvitesHolder.removeClass('show-invites-holder').slideUp("fast");
                              $this.removeClass('buying-club-selected');
                         } else {
                              $buyingClubInvitesHolder.addClass('show-invites-holder').slideDown("fast");
                              $this.addClass('buying-club-selected');
                              // de-select other business type options if buying club is selected
                              $('.other-than-buying-club-input').prop('checked', false);
                              $('#id_account-online_only').prop('checked', false);
                         }
                    });

                    $(document).on( "change", ".other-than-buying-club-input", function(e) {
                         console.log('other than buying club change');
                         var $this = $(this);

                         var $buyingClubInvitesHolder = $('#buying-club-additional-invites-holder');
                         var $buyingClubInput = $('#id_account-buying_club');
                         
                         if ($buyingClubInvitesHolder.hasClass('show-invites-holder')) {
                              $buyingClubInvitesHolder.removeClass('show-invites-holder').slideUp("fast");
                              $buyingClubInput.removeClass('buying-club-selected');
                              // de-select buying club and online only if other options chosen
                              $buyingClubInput.prop('checked', false);
                         }
                         // de-select other options chosen
                         $('#id_account-online_only').prop('checked', false);
                    });

                    $(document).on( "change", "#id_account-online_only", function(e) {
                         console.log('other than buying club change');
                         var $this = $(this);
                         // de-select buying club and other than fields
                         var $buyingClubInvitesHolder = $('#buying-club-additional-invites-holder');
                         var $buyingClubInput = $('#id_account-buying_club');
                         
                         if ($buyingClubInvitesHolder.hasClass('show-invites-holder')) {
                              $buyingClubInvitesHolder.removeClass('show-invites-holder').slideUp("fast");
                              $buyingClubInput.removeClass('buying-club-selected');
                              // de-select buying club and online only if other options chosen
                              $buyingClubInput.prop('checked', false);
                         }
                         $('.other-than-buying-club-input').prop('checked', false);
                    });

                    $(document).on( "click", ".btn-add-another-buyer-member", function(e) {
                         var $this = $(this);
                         // update current count 
                         buyingClub.currentCount++;
                         // log
                         console.log(buyingClub.currentCount);
                         // update input count val
                         buyingClub.buyerMemberCountInput.val(buyingClub.currentCount);

                         buyingClub.addNewRowHtml();
                    });

                    $(document).on( "click", ".btn-remove-buyer-member", function(e) {
                         var $this = $(this);
                         var $btnEl = $this;
                         // update current count 
                         buyingClub.currentCount--;
                         // log
                         console.log(buyingClub.currentCount);
                         // update input count val
                         buyingClub.buyerMemberCountInput.val(buyingClub.currentCount);
                         // remove row
                         buyingClub.removeRow($btnEl);
                    });
                    
                    

                    
               });

          </script>

     </body>
</html>
