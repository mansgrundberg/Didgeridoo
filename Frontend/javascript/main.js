var start = true;

//Hämta String liveaudio och sätt .sr-iframe src till liveaudio.  

$(document).ready(function() {
$.ajax({
url: 'http://localhost:5000/220',
type: "GET",
dataType: "json",
success: function (data) {
    var iframe =  $('.sr-iframe');
    var src = data['channel']['liveaudio'];
    $(iframe).attr('src', src);
    alert(data);
}
});
   

    $('h3').click(function (event) {
        disco(this);
    });

    $('.button').click(function(){
        if(start){
            $('body').addClass('animation');
            $('.button').html("STOP")
            start = false;
        }else{
            $('body').removeClass('animation');
            $('.button').html("DISCO TIME")
            start = true;
        }
    });
    
});    

function disco(element) {
    $(element).html("A nine volt battery from japan It's got the beat A little tape deck in my hand It's so so sweet Keeps me rockin' 'round the clock I don't mind it I got the beatbox, the beatbox, Just a beatin' that box Beatbox, beatbox One see-90's back to back Jumpin' on that jack Don't play with fire or Unplug my wire, beg or steal It's not for hire Keeps me rockin' 'round the clock I don't…");
}