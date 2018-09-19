
$(document).ready(function(){
 validation.contactUsForm('#id_contact-form');
    $(document).on('click','.contact-form__submit',function(event){
        event.preventDefault();
		 
        var  $validFlag = $("#id_contact-form").valid();
        if($validFlag){
            
            showLoadingScreen();
            sendContactDetails(); 
        }

    });
});

function sendContactDetails(){
  clearErrorMsg();
    var jsonData={};
     jsonData['name']=$('#id_name').val();
     jsonData['email']=$('#id_email').val();
     jsonData['phoneno']=$('#id_phone_number').val();
     jsonData['subject']=$('#id_reason option:selected').text();
     jsonData['message']=$('#id_message').val();
    $.get("/services/contact",jsonData,function(){

    }).done(function(data){
hideLoadingScreen();
        if(data == "Success"){
		showProdErrorMessage("Your message has been sent! Thanks for contacting us, we'll be in touch with you soon.");
        }else{
			showProdErrorMessage("Failed to submit this time. Please submit later.");
        }
    }).fail(function(data){
        hideLoadingScreen();

		 showProdErrorMessage("Failed to submit this time. Please submit later.");

    });


      
}