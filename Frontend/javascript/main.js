var start = true;

//Hämta String liveaudio och sätt .sr-iframe src till liveaudio.  

$(document).ready(function() {

    changeChannel('164'); 
   
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

function spotifyNotFound(element){
    $(element).attr('src', 'https://open.spotify.com/embed/track/6mgAlVU30jzkGq9Yap4sj7');
}

function youtubeNotFound(element){
    $(element).attr('src', 'https://www.youtube.com/embed/Y7-pRhOr4Vs');
}

function changeChannel(id){
    $.ajax({
        url: 'http://localhost:5000/'+id,
        timeout: 8000,
        type: "GET",
        dataType: "json",
        success: function (data) {
            var audio =  $('audio');
            $(audio).attr('src', data['channel']['liveaudio']);
            
            var spotifyPrev = $('.spotify-prev-iframe');
            var spotifyPresent = $('.spotify-present-iframe');
            var spotifyNext = $('.spotify-next-iframe');

            try{
                var previousSongSpotifyLink = data['previoussong']['spotifyLink'];
                $(spotifyPrev).attr('src', previousSongSpotifyLink);
                if(previousSongSpotifyLink==''){
                    spotifyNotFound(spotifyPrev);
                }
            }catch(err){
                console.log(err.message);
                spotifyNotFound(spotifyPrev);
            }
              try{
                var songSpotifyLink = data['song']['spotifyLink'];
                $(spotifyPresent).attr('src', songSpotifyLink);
                if(songSpotifyLink==''){
                    spotifyNotFound(spotifyPresent);
                }
            }catch(err){
                console.log(err.message);
                spotifyNotFound(spotifyPresent);
            }
              try{
                var nextSongSpotifyLink = data['nextsong']['spotifyLink'];
                $(spotifyNext).attr('src', nextSongSpotifyLink);
                if(nextSongSpotifyLink==''){
                    spotifyNotFound(spotifyNext);
                }
            }catch(err){
                console.log(err.message);
                spotifyNotFound(spotifyNext);
            }

            var youtubePrev = $('.youtube-prev-iframe');
            var youtubePresent = $('.youtube-present-iframe');
            var youtubeNext = $('.youtube-next-iframe');

            try{
                var previousSongYoutubeLink = data['previoussong']['youtubeLink'];
                $(youtubePrev).attr('src', previousSongYoutubeLink);
                if(previousSongYoutubeLink==''){
                    youtubeNotFound(youtubePrev);
                }
            }catch(err){
                console.log(err.message);
                youtubeNotFound(youtubePrev);
            }
             try{
                var songYoutubeLink = data['song']['youtubeLink'];
                $(youtubePresent).attr('src', songYoutubeLink);
                if(songYoutubeLink==''){
                    youtubeNotFound(youtubePresent);
                }
            }catch(err){
                console.log(err.message);
                youtubeNotFound(youtubePresent);   
            }
             try{
                var nextSongYoutubeLink = data['nextsong']['youtubeLink'];
                $(youtubeNext).attr('src', nextSongYoutubeLink);
                if(nextSongYoutubeLink==''){
                    youtubeNotFound(youtubeNext);
                }
            }catch(err){
                console.log(err.message);
                youtubeNotFound(youtubeNext);
            }       
        }
    });
}