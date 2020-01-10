var start = true;

//Hämta String liveaudio och sätt .sr-iframe src till liveaudio.  

$(document).ready(function() {

    $.ajax({
        url: 'http://localhost:5000/220',
        timeout: 4000,
        type: "GET",
        dataType: "json",
        success: function (data) {
        
        var audio =  $('audio');
        $(audio).attr('src', data['channel']['liveaudio']);
        
        var spotifyPrev = $('.spotify-prev-iframe');
        var spotifyPresent = $('.spotify-present-iframe');
        var spotifyNext = $('.spotify-next-iframe');

        var nextSongSpotifyLink = data['nextsong']['spotifyLink'];
        var previousSongSpotifyLink = data['previoussong']['spotifyLink'];
        var songSpotifyLink = data['song']['spotifyLink'];
        
        $(spotifyPrev).attr('src', previousSongSpotifyLink);
        $(spotifyPresent).attr('src', songSpotifyLink);
        $(spotifyNext).attr('src', nextSongSpotifyLink);
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

function changeChannel(id){
    $.ajax({
        url: 'http://localhost:5000/'+id,
        type: "GET",
        dataType: "json",
        success: function (data) {
        var audio =  $('audio');
        $(audio).attr('src', data['channel']['liveaudio']);
        
        
        var spotifyPrev = $('.spotify-prev-iframe');
        var spotifyPresent = $('.spotify-present-iframe');
        var spotifyNext = $('.spotify-next-iframe');

        var nextSongSpotifyLink = data['nextsong']['spotifyLink'];
        var previousSongSpotifyLink = data['previoussong']['spotifyLink'];
        var songSpotifyLink = data['song']['spotifyLink'];
        
        $(spotifyPrev).attr('src', previousSongSpotifyLink);
        $(spotifyPresent).attr('src', songSpotifyLink);
        $(spotifyNext).attr('src', nextSongSpotifyLink);
        }
    });
}