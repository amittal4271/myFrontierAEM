$(function() {
// Handle enter key press  
$("#email-input").keyup(function(event){
if(event.keyCode == 13){
$("#submit-email").click();
}
})  
});

$("#newsletter-form").validate({
  submitHandler: function() {  
  // Simulate success
  $.ajax = ajax_response('{ "title": "Success registering to mialing list" }', true); 
  sendAjaxCall(); // Function that calls $.ajax
  /*
  // Simulate error
  $.ajax = ajax_response('{ "error": "Error registering to mialing list" }', false); 
  sendAjaxCall(); // Function that calls $.ajax 
  */
  }
});

function sendAjaxCall() {
  $.ajax({
    type: "POST",
    url: $('#url').val(),
    // data: {…},
    success: function (results) {
      alert(results);
    },
    error: function (results) {
      alert(results);
    } 
  });
};


function ajax_response(response, success) {
  return function (params) {
    if (success) {
      params.success(response);
    } else {
      params.error(response);
    }
  };
}
