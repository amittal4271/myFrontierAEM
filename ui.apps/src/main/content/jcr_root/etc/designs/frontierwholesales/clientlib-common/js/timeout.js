function setup() {
    this.addEventListener("mousemove", resetTimer, false);
    this.addEventListener("mousedown", resetTimer, false);
    this.addEventListener("keypress", resetTimer, false);
    this.addEventListener("DOMMouseScroll", resetTimer, false);
    this.addEventListener("mousewheel", resetTimer, false);
    this.addEventListener("touchmove", resetTimer, false);
    this.addEventListener("MSPointerMove", resetTimer, false);
 
    startTimer();
}

setup();

function startTimer() {
    // wait 20 mins before calling goInactive
   var timeoutID = window.setTimeout(goInactive, 1200000);
}
 
function resetTimer(e) {
    window.clearTimeout(timeoutID);
 
    goActive();
}

function goInactive() {
    // remove token and redirect to login page when page is inactive
     document.cookie="CustomerData=;Max-Age=-99999999;path=/;";
    window.location.href=redirectToLogin();
}
 
function goActive() {
    // do something
         
    startTimer();
}