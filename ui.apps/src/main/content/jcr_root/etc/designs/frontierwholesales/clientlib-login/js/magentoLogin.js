
$(document).ready(function(){
   checkCookie(); 
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

   
    var userName = $("#id_username").val();
    var password= $("#id_password").val();
    clearErrorMessages();
    var valid =  validateForm(userName,password);
    if(valid){ 
       
         $.ajax({
            type: "POST",  
            url: '/frontier_auth_handler', 

            headers:{

                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization':'Basic '+btoa(userName+":"+password)
            },
            success:function(data,textStatus,jqXHR ){
               console.log(textStatus);
                window.location.href=getRedirectPath();
                
            }, error: function(XMLHttpRequest, textStatus, errorThrown) { 
                $('.login-text').css('display','block');
            }

        }); 
      
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

function magentoLogin(userName,password){
     var loginURL = '/services/loginservlet';
   var jsonData = {};
    var bReturn = false;
    jsonData['username']=userName;
    jsonData['password']=password;
     $.ajax({
         url: loginURL,
         method: 'POST',
        data:jsonData,
         success: function(results){
             console.log('logged into Magento... ');
             var results = JSON.parse(results);
             if(results.token == 'Token Error'){
                 $('.login-text').css('display','block');
                 return false;
             }else{
                 window.location.href=getRedirectPath();
             }
         },error: function(error){
             console.log('error is '+error);
              $('.login-text').css('display','block');
             return false;
         }
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


