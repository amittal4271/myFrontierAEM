$(document).ready(function(){
    $('.contact-form-submit').on('click',function(event){
        event.preventDefault();
        sendContactDetails();
    })
});

function sendContactDetails(){
    var jsonData={};
     jsonData['name']=$('#id_name').val();
     jsonData['email']=$('#id_email').val();
     jsonData['phoneno']=$('#id_phone_number').val();
     jsonData['subject']=$('#id_reason option:selected').text();
     jsonData['message']=$('#id_message').val();
    $.ajax({
       url: "/services/contact",
        method: "GET",
        data:jsonData,
        beforeSend:function(xhr){
                xhr.overrideMimeType('application/json');
            }
        
    }).done(function(data){
        console.log("successfully send "+data);
    }).fail(function(error){
        console.log('failure '+error);
    });
}