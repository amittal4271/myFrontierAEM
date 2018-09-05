
$(document).ready(function(){
   checkCookie(); 
   if($('.login-section').length > 0){
       var userToken = getUserToken();
       if(userToken.startsWith('Bearer') && userToken.trim().length > 0){
           location.href='/content/frontierwholesales/en/myaccount.html';
       }
   }
    $("#id_username").keyup(function(event){ 
        if(event.keyCode == 13){ 
            $("#btn-login").click(); 
        }
    });

$("#btn-login").click(function(e){ 
    e.preventDefault(); 
    if($('#rememberCheckbox').is(":checked")) { 
        user = $("#id_username").val(); 
        if (user != "" && user != null) { 
            setCookie("usr_c",user,30);
        } 
    }

   var serverURL = $('#serverURL').val();
    var userName = $("#id_username").val();
    var password= $("#id_password").val();
    clearErrorMessages();
    var valid =  validateForm(userName,password);
    if(valid){ 
       login(serverURL,userName,password);
        
    } else{ 
       
       return false;
    } 
}); 
    
    

});

function validateEmail(mail) 
{
  var emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if(mail.match(emailFormat)){
        return true;
    }else{
        return false;
    }
  
}

function magentoLogin(serverURL,userName,password){
     var loginURL = serverURL+'/rest/V1/integration/customer/token';
   var jsonData = {};
    var bReturn = false;
    jsonData['username']=userName;
    jsonData['password']=password;
     $.ajax({
         url: loginURL,
         method: 'POST',
        data:JSON.stringify(jsonData),
          crossDomain: "true",
        headers:{
            "content-Type":"application/json",
            "Access-Control-Allow-Origin":serverURL,
            "Access-Control-Allow-Credentials":"true"
        }}).done(function(results){
         console.log('logged into Magento... '+results);
          var regx=new RegExp("\"","g");
        results=results.replace(regx,"");
         //var token = "MagentoUserToken=Bearer "+results+";";
           var jsonData={};
         jsonData['token']="Bearer "+results;
        
         var cookieData = "CustomerData="+JSON.stringify(jsonData)+"; path=/";
        
         addCookie(cookieData);
        
            window.location.href=Granite.HTTP.externalize(getRedirectPath());
     }).fail(function(error){
         console.log('error is '+error);
              $('.login-text').css('display','block');
             return false;
     });
        
     
}

function getRedirectPath(){
        return "/content/frontierwholesales/en/myaccount.html"; 

    }
    
    function setCookie(cname,cvalue,exdays){
        var d = new Date(); 
        d.setTime(d.getTime() + (exdays*24*60*60*1000)); var expires = "expires=" + d.toGMTString(); 
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";


    }
    
    function getCookie(cname){
        var name = cname + "="; 
        var decodedCookie = decodeURIComponent(document.cookie); 
        var ca = decodedCookie.split(';'); 
        for(var i = 0; i < ca.length; i++) { 
            var c = ca[i]; 
            while (c.charAt(0) == ' ') { 
                c = c.substring(1); 
            } 
            if (c.indexOf(name) == 0) { 
                return c.substring(name.length, c.length); 
            } 
        } 
        return "";

    }
    
    function checkCookie () { 
        var user=getCookie("usr_c"); 
        if (user != "") { 
            $("#id_username").val(user); 
        } 
    }
    
    // Function for validating Login form  
    function validateForm (userId,password) { 
        var valid = false; 
       
        if((userId.length >0) && (password.length >0)){ 
            valid = true; 
        } 
        
        if(userId.length == 0){
            $('.userid-text').css('display','block');
            
        }
        if(password.length == 0){
            $('.password-text').css('display','block');
        }
      
        
        
        return valid; 
    }



function clearErrorMessages(){
    $('.userid-text').css('display','none');
    $('.login-text').css('display','none');
    $('.password-text').css('display','none');
   
}




function login(serverURL,username,password){
    showLoadingScreen();
    Frontier.MagentoServices.magentoLogin(serverURL,username,password).done(function(results){
        var regx=new RegExp("\"","g");
        results=results.replace(regx,"");
         
           var jsonData={};
         jsonData['token']="Bearer "+results;
        
         var cookieData = "CustomerData="+JSON.stringify(jsonData)+"; path=/";
        
         addCookie(cookieData);
        Frontier.MagentoServices.getUserRole(serverURL).done(function(customer){
            hideLoadingScreen();
             try{
               
                 var jsonData={};
                 jsonData['token']="Bearer "+results;
                 jsonData['email']=customer.frontier_customer.email;
                // jsonData['id']=customer.frontier_customer.id;
               //  jsonData['role_name']=customer.role_name;
               //  jsonData['role_id']=customer.role_id;
                // jsonData['company_name']=customer.company_name;
               //  jsonData['company_id']=customer.company_id;
               //  jsonData['group_id']=customer.frontier_customer.group_id;
                 var cookieData = "CustomerData="+JSON.stringify(jsonData)+"; path=/";
                addCookie(cookieData);
                  window.location.href=Granite.HTTP.externalize(getRedirectPath());
            }catch(error){
                console.log("invalid json format "+error);
                enableErrorMsg('403');
                return false;
            }
        }).fail(function(error){
            console.log("failed to get customer details "+error);
            hideLoadingScreen();
             $('.login-text').css('display','block');
             return false;
        });
    }).fail(function(error){
         console.log("login failure "+error);
        hideLoadingScreen();
         $('.login-text').css('display','block');
             return false;
    });
}