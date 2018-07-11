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
                              
                              <div class="account-heading-holder table-below">
                                   <h2 class="account-heading pull-left">Patronage</h2>
                                   <div class="patronageShareSection">
                                        <p>Share Balance: <span class="patronageShare">$.00</span></p>
                                        <p>Share Requirement: <span class="patronageShare"> $597.85</span></p>
                                   </div>
                              </div>
                              <div class="account-information-holder">
                                   <div class="grid-table-header order-information-header">
                                        <div class="each-order-header patronage-header-year grid-header">Fiscal Year</div>
                                        <div class="each-order-header patronage-header-allocation grid-header">Total Allocation</div>
                                        <div class="each-order-header patronage-header-retained grid-header">Retained</div>
                                        <div class="each-order-header patronage-header-withholding grid-header">Federal Withholding</div>
                                        <div class="each-order-header patronage-header-distributions grid-header">Cash Distributions</div>
                                        <div class="each-order-header patronage-header-adjustments grid-header">Distribution Adjustments</div>
                                   </div>

                                   <div class="order-grid grid-table-data invites-data">
                                        <div class="row-each-order">
                                             <div class="information-holder order-information-holder">
                                                  <div class="each-order-section patronage-year-section">
                                                       <a href="#viewDetails" data-toggle="collapse" class="btn-view-details" title="View Details">
                                                            <span class="fiscal-year-link-text">2015</span>
                                                       </a>
                                                  </div>
                                                  <div class="each-order-section patronage-allocation-section">
                                                       $966.54
                                                  </div>
                                                  <div class="each-order-section patronage-retained-section">
                                                       $.00
                                                  </div>
                                                  <div class="each-order-section patronage-withholding-section">
                                                       $.00
                                                  </div>
                                                  <div class="each-order-section patronage-distributions-section">
                                                       $.00
                                                  </div>
                                                  <div class="each-order-section patronage-adjustments-section">
                                                       $966.54
                                                  </div>
                                             </div>
                                        </div>
                                        
                                        <div class="collapse viewDetailSection" id="viewDetails">
                                             <div class="information-holder order-information-holder">
                                                  <div class="grid-table-header order-information-header">
                                                       <div class="each-order-header patronage-inner-header-year grid-header">Fiscal Year</div>
                                                       <div class="each-order-header patronage-inner-header-date grid-header">Check Date</div>
                                                       <div class="each-order-header patronage-inner-header-type grid-header">Type</div>
                                                       <div class="each-order-header patronage-inner-header-amount grid-header">Amount</div>
                                                       <div class="each-order-header patronage-inner-header-cash grid-header">Cash Ind</div>
                                                       <div class="each-order-header patronage-inner-header-check grid-header">Check Number</div>
                                                       <div class="each-order-header patronage-inner-header-comment grid-header">Comment</div>
                                                       <span class="pull-right">
                                                            <a class="viewDetailsDismiss glyphicon glyphicon-remove" href="#viewDetails" data-toggle="collapse"></a>
                                                       </span>
                                                  </div>
                                             </div>
                                             <div class="row-each-order">
                                                  <div class="information-holder order-information-holder">
                                                       <div class="each-order-section patronage-inner-year-section">
                                                            2015
                                                       </div>
                                                       <div class="each-order-section patronage-inner-date-section">
                                                            3/24/2016
                                                       </div>
                                                       <div class="each-order-section patronage-inner-type-section">
                                                            I
                                                       </div>
                                                       <div class="each-order-section patronage-inner-amount-section">
                                                            $338.29
                                                       </div>
                                                       <div class="each-order-section patronage-inner-cash-section">
                                                            False
                                                       </div>
                                                       <div class="each-order-section patronage-inner-check-section">
                                                            57407
                                                       </div>
                                                       <div class="each-order-section patronage-inner-check-comment">
                                                            35.00000% Initial Allocation
                                                       </div>
                                                  </div>
                                             </div>
                                        </div>
                                        <div class="row-each-order">
                                             <div class="information-holder order-information-holder">
                                                  <div class="each-order-section patronage-year-section">
                                                       <a href="#viewDetails2" data-toggle="collapse" class="btn-view-details" title="View Details">
                                                            <span class="fiscal-year-link-text">2014</span>
                                                       </a>
                                                  </div>
                                                  <div class="each-order-section patronage-allocation-section">
                                                       $691.86
                                                  </div>
                                                  <div class="each-order-section patronage-retained-section">
                                                       $.00
                                                  </div>
                                                  <div class="each-order-section patronage-withholding-section">
                                                       $.00
                                                  </div>
                                                  <div class="each-order-section patronage-distributions-section">
                                                       $.00
                                                  </div>
                                                  <div class="each-order-section patronage-adjustments-section">
                                                       $691.86
                                                  </div>
                                             </div>
                                        </div>
                                        
                                        <div class="collapse viewDetailSection" id="viewDetails2">
                                             <div class="information-holder order-information-holder">
                                                  <div class="grid-table-header order-information-header">
                                                       <div class="each-order-header patronage-inner-header-year grid-header">Fiscal Year</div>
                                                       <div class="each-order-header patronage-inner-header-date grid-header">Check Date</div>
                                                       <div class="each-order-header patronage-inner-header-type grid-header">Type</div>
                                                       <div class="each-order-header patronage-inner-header-amount grid-header">Amount</div>
                                                       <div class="each-order-header patronage-inner-header-cash grid-header">Cash Ind</div>
                                                       <div class="each-order-header patronage-inner-header-check grid-header">Check Number</div>
                                                       <div class="each-order-header patronage-inner-header-comment grid-header">Comment</div>
                                                       <span class="pull-right">
                                                            <a class="viewDetailsDismiss glyphicon glyphicon-remove" href="#viewDetails2" data-toggle="collapse"></a>
                                                       </span>
                                                  </div>
                                             </div>
                                             <div class="row-each-order">
                                                  <div class="information-holder order-information-holder">
                                                       <div class="each-order-section patronage-inner-year-section">
                                                            2014
                                                       </div>
                                                       <div class="each-order-section patronage-inner-date-section">
                                                            3/24/2015
                                                       </div>
                                                       <div class="each-order-section patronage-inner-type-section">
                                                            I
                                                       </div>
                                                       <div class="each-order-section patronage-inner-amount-section">
                                                            $338.29
                                                       </div>
                                                       <div class="each-order-section patronage-inner-cash-section">
                                                            False
                                                       </div>
                                                       <div class="each-order-section patronage-inner-check-section">
                                                            57407
                                                       </div>
                                                       <div class="each-order-section patronage-inner-check-comment">
                                                            35.00000% Initial Allocation
                                                       </div>
                                                  </div>
                                             </div>
                                        </div>
                                   </div>
                              </div>
                         </section>
                    </div>
               </section>
          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>
     </body>
</html>
