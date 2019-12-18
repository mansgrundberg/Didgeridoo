var start = true;

$(document).ready(function() {
    $('h3').click(function (event) {
        disco(this);
    });

    $('.button').click(function(){
    	if(start){
    		$('#main').addClass('animation');
    		$('.button').html("STOP")
    		start = false;
    	}else{
    		$('#main').removeClass('animation');
    		$('.button').html("DISCO TIME")
        	start = true;
    	}
    });
    var btn = $('.musicbutton');
    
    btn.click(function() {
    
        btn.toggleClass('paused');
    
    return false;
    });
    
});    

function menuButton(x) {
  x.classList.toggle("change");
}

function disco(element) {
    $(element).html("A nine volt battery from japan It's got the beat A little tape deck in my hand It's so so sweet Keeps me rockin' 'round the clock I don't mind it I got the beatbox, the beatbox, Just a beatin' that box Beatbox, beatbox One see-90's back to back Jumpin' on that jack Don't play with fire or Unplug my wire, beg or steal It's not for hire Keeps me rockin' 'round the clock I don't…");
}