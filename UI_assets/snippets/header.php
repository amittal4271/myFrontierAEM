<!--<div id="search-overlay"></div>-->
<header id="header">
     <div id="left-header-icons-mobile">
          <button id="mobile-menu-icon" class="header-mobile-icon" type="button">
               <span class="menu-icon"><span class="glyphicon glyphicon-menu-hamburger"></span></span>
               <span class="menu-text">Menu</span>
          </button>
          <button id="mobile-search-icon" class="header-mobile-icon" type="button">
               <span class="glyphicon glyphicon-search"></span>
               <span class="search-text visuallyhidden">Search</span>
          </button>
     </div>
     <div id="logo-header">
          <a href="./" class="logo-link" title="Frontier Co-op Wholesale">
               <span class="logo-icon-header">
                    <img src="https://dh6qdiyp93d9u.cloudfront.net/static/frontierwholesale/images/logo.6694741e49f1.svg"/>
                    <span class="visuallyhidden">Frontier Co-op Wholesale</span>
               </span>
               <span class="logo-text" aria-hidden="">Wholesale Store</span>
          </a>
     </div>
     <div id="search-header-holder">
          <form action="" class="search-form form" id="search-form">
               <input type="text" class="form-control search-input" placeholder="Search &amp; Save..." name="q" autocomplete="off">
               <button class="btn btn-green-dark" id="btn-search">Search</button>
          </form>
     </div>
     <div id="secondary-nav-holder">
          <ul class="user-menu">
               <li><a id="header-quick-order-link" href="#">Quick Order</a></li>
               <li><a id="header-help-link" href="#">Help</a>
                    <ul id="header-help-flyout" class="header-flyout-list">
                         <li><a href="#">Contact</a></li>
                         <li><a href="#">FAQ</a></li>
                         <li><a href="#">Glossary</a></li>
                    </ul>
               </li>
               <li><a id="header-join-link" href="#">Join</a></li>
               <li><a id="header-my-account-link" href="#">My Account</a>
                    <ul id="header-account-flyout" class="header-flyout-list">
                         <!-- showing account links -->
                         <!--<li><a href="#">Join</a></li>-->
                         <li><a href="#">Address Book</a></li>
                         <li><a href="#">Orders</a></li>
                         <li><a href="#">Wish List</a></li>
                         <li><a href="#">Sign Out</a></li>
                    </ul>
               </li>
          </ul>
     </div>
     <!-- the class owner-account-cart-holder should only be added when the owner is logged in -->
     <div id="cart-holder" class="owner-account-cart-holder">
          <a href="#" class="btn btn-cart-display cart-display" title="Cart">
               <span class="icon-cart">
                    <span class="glyphicon glyphicon-shopping-cart"></span>
                    <span class="cart-count quantity">0</span>
               </span>
               <span class="cart-amount">My Total: <span class="amount">$130.00</span></span>
          </a>

          <!-- this link should only be shown when owner is logged in -->
          <a href="#" class="btn btn-cart-display cart-display-owner-total" title="Cart">
               <span class="icon-cart">
                    <span class="glyphicon glyphicon-shopping-cart"></span>
               </span>
               <span class="cart-amount">Our Total: <span class="amount">$1,230.00</span></span>
          </a>

          <div class="mini-cart-holder">
               <div class="each-mini-cart-item">
                    <div class="mini-cart-image-holder">
                         <img src="../frontierwholesales/images/mini-cart-item-one.jpg"/>
                    </div>
                    <div class="mini-cart-info-holder">
                         <div class="mini-cart-name">Handy Pantry Arugula Microgreen Kit</div>
                         <div class="mini-cart-price">
                              <span class="original-price strike-through-price">$3.20</span>
                              <span class="sale-now-price">Now $2.99</span>
                         </div>
                    </div>
                    <div class="mini-cart-remove-holder">
                         <button class="btn btn-mini-cart-remove">
                              <span class="glyphicon glyphicon-remove"></span>
                              <span class="remove-text">Remove Item</span>
                         </button>
                    </div>
               </div>

               <div class="each-mini-cart-item">
                    <div class="mini-cart-image-holder">
                         <img src="../frontierwholesales/images/mini-cart-item-two.jpg"/>
                    </div>
                    <div class="mini-cart-info-holder">
                         <div class="mini-cart-name">Handy Pantry Arugula Microgreen Kit</div>
                         <div class="mini-cart-price">
                              <span class="original-price strike-through-price">$3.20</span>
                              <span class="sale-now-price">Now $2.99</span>
                         </div>
                    </div>
                    <div class="mini-cart-remove-holder">
                         <button class="btn btn-mini-cart-remove">
                              <span class="glyphicon glyphicon-remove"></span>
                              <span class="remove-text">Remove Item</span>
                         </button>
                    </div>
               </div>

               <div class="mini-cart-subtotal-holder">
                    <div class="subtotal-text">Subtotal</div>
                    <div class="subtotal-amount">$44.50</div>
               </div>

               <div class="mini-cart-go-to-cart-holder">
                    <a href="#" class="btn btn-light-green btn-mini-cart-go-to-cart">Go to Cart</a>
               </div>

          </div>
          
     </div>
     <div id="right-header-icons-mobile">
          <a href="#" id="mobile-account-icon" class="header-mobile-icon">
               <span class="glyphicon glyphicon-user"></span>
               <span class="visuallyhidden">Account</span>
          </a>
          <a id="mobile-cart-icon" href="#" class="header-mobile-icon">
               <span class="icon-cart">
                    <span class="glyphicon glyphicon-shopping-cart"></span>
                    <span class="cart-count quantity">0</span>
               </span>
               <span class="visuallyhidden">Cart</span>
          </a>
     </div>
</header>


<?php include(dirname(__FILE__).'/navigation.php');?>

