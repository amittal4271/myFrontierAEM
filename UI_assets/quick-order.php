<!DOCTYPE html>
<html lang="en">
     <head>
          <?php include(dirname(__FILE__).'/snippets/header-files-include.php');?>
          <style>
          #sub-nav-holder-desktop {display: none;}
          </style>
     </head>

     <body>

          <?php include(dirname(__FILE__).'/snippets/header.php');?>

          <main class="main-content-container account-section-max-1200">

               <section class="blog-list-page">
                    <div class="container">

                         <div class="search-header-holder article-header-holder">
                              <div id="search-heading">
                                   <h1 class="plp-search-text-heading">Quick Order</h1>
                                   <span class="showing-number-of-items">
                                        Enter item numbers and quantity to quickly add items to your cart
                                   </span>
                              </div>
                         </div>

                         <div id="quick-order-form-holder">
                              <input type="hidden" value="5" id="quick-order-count-input"/>

                              <div id="quick-order-section-1" class="each-quick-order-section">
                                   <div class="form-group item-group">
                                        <label for="item_code_1">Item Code</label>
                                        <input id="item_code_1" name="item_code_1" placeholder="Code" type="text" class="form-control">
                                        <div class="quick-add-caption hidden">
                                             Sorry, no product found!
                                        </div>
                                   </div>
                                   <div class="form-group qty-group">
                                        <label for="item_qty_1">Qty</label>
                                        <input id="item_qty_1" name="item_qty_1" placeholder="Qty" type="text" class="form-control">
                                   </div>
                                   <div class="form-group price-group">
                                        <label for="total_price_1">Total Price</label>
                                        <input id="total_price_1" readonly name="total_price_1" type="text" class="form-control">
                                   </div>
                                   <div class="form-group remove-group">
                                        <label for="remove_1">Remove</label>
                                        <button type="button" class="btn btn-only-green-icon">
                                             <span class="address-link-text visuallyhidden">Remove</span>
                                             <span class="glyphicon glyphicon-remove"></span>
                                        </button>
                                   </div>
                              </div>

                              <div id="quick-order-section-2" class="each-quick-order-section">
                                   <div class="form-group item-group">
                                        <label for="item_code_2">Item Code</label>
                                        <input id="item_code_2" name="item_code_2" placeholder="Code" type="text" class="form-control">
                                        <div class="quick-add-caption hidden">
                                             Sorry, no product found!
                                        </div>
                                   </div>
                                   <div class="form-group qty-group">
                                        <label for="item_qty_2">Qty</label>
                                        <input id="item_qty_2" name="item_qty_2" placeholder="Qty" type="text" class="form-control">
                                   </div>
                                   <div class="form-group price-group">
                                        <label for="total_price_2">Total Price</label>
                                        <input id="total_price_2" readonly name="total_price_2" type="text" class="form-control">
                                   </div>
                                   <div class="form-group remove-group">
                                        <label for="remove_2">Remove</label>
                                        <button type="button" class="btn btn-only-green-icon">
                                             <span class="address-link-text visuallyhidden">Remove</span>
                                             <span class="glyphicon glyphicon-remove"></span>
                                        </button>
                                   </div>
                              </div>

                              <div id="quick-order-section-3" class="each-quick-order-section">
                                   <div class="form-group item-group">
                                        <label for="item_code_3">Item Code</label>
                                        <input id="item_code_3" name="item_code_3" placeholder="Code" type="text" class="form-control">
                                        <div class="quick-add-caption hidden">
                                             Sorry, no product found!
                                        </div>
                                   </div>
                                   <div class="form-group qty-group">
                                        <label for="item_qty_3">Qty</label>
                                        <input id="item_qty_3" name="item_qty_3" placeholder="Qty" type="text" class="form-control">
                                   </div>
                                   <div class="form-group price-group">
                                        <label for="total_price_3">Total Price</label>
                                        <input id="total_price_3" readonly name="total_price_3" type="text" class="form-control">
                                   </div>
                                   <div class="form-group remove-group">
                                        <label for="remove_3">Remove</label>
                                        <button type="button" class="btn btn-only-green-icon">
                                             <span class="address-link-text visuallyhidden">Remove</span>
                                             <span class="glyphicon glyphicon-remove"></span>
                                        </button>
                                   </div>
                              </div>

                              <div id="quick-order-section-4" class="each-quick-order-section">
                                   <div class="form-group item-group">
                                        <label for="item_code_4">Item Code</label>
                                        <input id="item_code_4" name="item_code_4" placeholder="Code" type="text" class="form-control">
                                        <div class="quick-add-caption hidden">
                                             Sorry, no product found!
                                        </div>
                                   </div>
                                   <div class="form-group qty-group">
                                        <label for="item_qty_4">Qty</label>
                                        <input id="item_qty_4" name="item_qty_4" placeholder="Qty" type="text" class="form-control">
                                   </div>
                                   <div class="form-group price-group">
                                        <label for="total_price_4">Total Price</label>
                                        <input id="total_price_4" readonly name="total_price_4" type="text" class="form-control">
                                   </div>
                                   <div class="form-group remove-group">
                                        <label for="remove_4">Remove</label>
                                        <button type="button" class="btn btn-only-green-icon">
                                             <span class="address-link-text visuallyhidden">Remove</span>
                                             <span class="glyphicon glyphicon-remove"></span>
                                        </button>
                                   </div>
                              </div>

                              <div id="quick-order-section-5" class="each-quick-order-section">
                                   <div class="form-group item-group">
                                        <label for="item_code_5">Item Code</label>
                                        <input id="item_code_5" name="item_code_5" placeholder="Code" type="text" class="form-control">
                                        <div class="quick-add-caption hidden">
                                             Sorry, no product found!
                                        </div>
                                   </div>
                                   <div class="form-group qty-group">
                                        <label for="item_qty_5">Qty</label>
                                        <input id="item_qty_5" name="item_qty_5" placeholder="Qty" type="text" class="form-control">
                                   </div>
                                   <div class="form-group price-group">
                                        <label for="total_price_5">Total Price</label>
                                        <input id="total_price_5" readonly name="total_price_5" type="text" class="form-control">
                                   </div>
                                   <div class="form-group remove-group">
                                        <label for="remove_5">Remove</label>
                                        <button type="button" class="btn btn-only-green-icon">
                                             <span class="address-link-text visuallyhidden">Remove</span>
                                             <span class="glyphicon glyphicon-remove"></span>
                                        </button>
                                   </div>
                              </div>

                              
                         </div>
                         <div class="quick-order-actions">
                              <button type="button" class="btn btn-link green-link btn-add-more-line-items">
                                   <span class="glyphicon glyphicon-plus"></span>Add Another Line
                              </button>
                         </div>
                         <div class="quick-order-actions">
                              <button type="button" class="btn btn-larger btn-light-green btn-add-all-line-items-to-cart">
                                   Add Items to Cart
                              </button>
                         </div>                        
                    </div>
               </section>

          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>
          <script>
               ;(function($) {

                    window.quickOrder = {
                         quickOrderCountInput: $('#quick-order-count-input'),
                         groupHolder: $('#quick-order-form-holder'),
                         countMin: 5,
                         countMax: 100,
                         currentCount: 5,

                         addNewRowHtml: function () {
                              var i;
                              for (i = 0; i < 5; i++){
                                   quickOrder.currentCount++;
                                   quickOrder.quickOrderCountInput.val(quickOrder.currentCount);
                                   var $newRowHtml = '';

                                   $newRowHtml += '<div id="quick-order-section-'+quickOrder.currentCount+'" class="each-quick-order-section">';
                                        $newRowHtml += '<div class="form-group item-group">';
                                             $newRowHtml += '<label for="item_code_'+quickOrder.currentCount+'">Item Code</label>';
                                             $newRowHtml += '<input id="item_code_'+quickOrder.currentCount+' name="item_code_'+quickOrder.currentCount+'" placeholder="Code" type="text" class="form-control">';
                                             $newRowHtml += '<div class="quick-add-caption hidden">';
                                                  $newRowHtml += 'Sorry, no product found!';
                                             $newRowHtml += '</div>';
                                        $newRowHtml += '</div>';     
                                        $newRowHtml += '<div class="form-group qty-group">';
                                             $newRowHtml += '<label for="item_qty_'+quickOrder.currentCount+'">Qty</label>';
                                             $newRowHtml += '<input id="item_qty_'+quickOrder.currentCount+'" name="item_qty_'+quickOrder.currentCount+'" placeholder="Qty" type="text" class="form-control">';
                                        $newRowHtml += '</div>';
                                        $newRowHtml += '<div class="form-group price-group">';
                                             $newRowHtml += '<label for="total_price_'+quickOrder.currentCount+'">Total Price</label>';
                                             $newRowHtml += '<input id="total_price_'+quickOrder.currentCount+'" name="total_price_'+quickOrder.currentCount+'" type="text" class="form-control">';
                                        $newRowHtml += '</div>';
                                        $newRowHtml += '<div class="form-group price-group">';
                                             $newRowHtml += '<label for="remove_'+quickOrder.currentCount+'">Remove</label>';
                                             $newRowHtml += '<button type="button" class="btn btn-only-green-icon btn-remove-quick-order">';
                                                  $newRowHtml += '<span class="address-link-text visuallyhidden">Remove</span>';
                                                  $newRowHtml += '<span class="glyphicon glyphicon-remove"></span>';
                                             $newRowHtml += '</button>';     
                                        $newRowHtml += '</div>';
                                   $newRowHtml += '</div>';
                                   
                                   quickOrder.groupHolder.append($newRowHtml);
                              }     
                         },

                         removeRow: function ($btnEl) {
                              // find parent invite group holder
                              var $quickOrderSectionRemoveHolder = $btnEl.parents('.each-quick-order-section');
                              // remove html
                              $quickOrderSectionRemoveHolder.remove();
                              quickOrder.updateLineItemCountsOnRemove();
                         },

                         updateLineItemCountsOnRemove: function () {
                              // we need to update numbering based on line item number that is removed
                              if (quickOrder.currentCount > 4) {
                                   $('.each-quick-order-section').each(function (index, value) {
                                        var $this = $(this);
                                        var $nameGroup = $this.children('.name-group');
                                        var $emailGroup = $this.children('.email-group');
                                        console.log(index+1);
                                        // set index to match numbers starting at 1
                                        var $newIndex = index+1;
                                        // reset html that has numbers according to new index
                                        $this.attr('id', 'quick-order-section-'+$newIndex);

                                        // set new index values on labels and inputs for name and email
                                        var $nameLabel = $nameGroup.find('label');
                                        var $nameInput = $nameGroup.find('input');

                                        $nameLabel.attr('for', 'id_invite-'+$newIndex+'-name');
                                        $nameInput.attr('id', 'id_invite-'+$newIndex+'-name');
                                        $nameInput.attr('name', 'invite_'+$newIndex+'_name');

                                        var $emailLabel = $emailGroup.find('label');
                                        var $emailInput = $emailGroup.find('input');

                                        $emailLabel.attr('for', 'id_invite-'+$newIndex+'-email');
                                        $emailInput.attr('id', 'id_invite-'+$newIndex+'-email');
                                        $emailInput.attr('name', 'invite_'+$newIndex+'_email');
                                   });
                              }
                         }
                    };

               })(jQuery);

               $(document).ready(function() {


                    $(document).on( "click", ".btn-add-more-line-items", function(e) {
                         var $this = $(this);
                         // update current count 
                         
                         // log
                         console.log(quickOrder.currentCount);
                         // update input count val
                         

                         quickOrder.addNewRowHtml();
                    });

                    $(document).on( "click", ".btn-remove-quick-order", function(e) {
                         var $this = $(this);
                         var $btnEl = $this;
                         // update current count 
                         quickOrder.currentCount--;
                         // log
                         console.log(quickOrder.currentCount);
                         // update input count val
                         quickOrder.quickOrderCountInput.val(quickOrder.currentCount);
                         // remove row
                         quickOrder.removeRow($btnEl);
                    });
                    
               });
          </script>
     </body>
</html>
