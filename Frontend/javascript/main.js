
/*
* jQuery-method which runs the code within it once the page DOM is ready to execute JavaScript. 
* The ready-function sets a global variable, start, which holds a boolean value for the disco function.
* The ready function also runs the changeChannel()-function and passes a channelID parameter.
*/

$(document).ready(function() {
    window.start = true;
    changeChannel('p3');
       
});    

/*
* Function which is called when the ajax request for a Spotify-link didn't return any value. 
*/

function spotifyNotFound(element){
    $(element).attr('src', 'https://open.spotify.com/embed/track/6mgAlVU30jzkGq9Yap4sj7');
}

/*
* Function which is called when the ajax request for a Youtube-link didn't return any value. 
*/

function youtubeNotFound(element){
    $(element).attr('src', 'https://www.youtube.com/embed/Y7-pRhOr4Vs');
}

/*
* The disco function..
*/

function disco(){
     if(start){
            $('body').addClass('animation');
            $('.button').html("STOP")
            start = false;
        }else{
            $('body').removeClass('animation');
            $('.button').html("DISCO TIME")
            start = true;
        }

}
function about(){
    $("body").prepend("<div id='backgroundModal' style='top: 0; bottom: 0; left: 0; right: 0; position: fixed; background-color: rgba(0,0,0,0.5); display:none; z-index: 5;'></div>");
        // Visar bakgrunden och aboutsidan
        $("#backgroundModal").fadeIn(500);
        $('#aboutpage').css({
            'display': 'block',
        });
        // Tar bort aboutsidan och bakgrundsskuggan
        $("#backgroundModal").on("click", function() {
            $("#backgroundModal").fadeOut(500);
            $('#aboutpage').css({
                'display': 'none',
            });
        });
}
    
/*
* Function which makes ajax-requests to the REST-APIs GET endpoint for the different radio channels. 
* The function is called on load of webbpage once and then when users clicks on specified channel buttons. 
* The channel buttons sends the belonging channel id to this function. 
* The funtion then fetches the response object and sets the Audio control with the fethed live audio mp3. 
* After that the function sets the text elements with the fetched channel name and the playing artist and song. 
* Then the function sets the iframe elements with the fetched embeded links 
* from Spotify and Youtube to the previous, current and next song , if found. 
*/

function changeChannel(id){
    myStopFunction();
    $.ajax({
        url: 'http://localhost:5000/v1/channels/'+id,
        type: "GET",
        dataType: "json",
        success: function (data) {
            $('audio').attr('src', data['channel']['liveaudio']);

            var channelName = $('#radioName');
            var titleAndArtist = $('#songTitleArtist');
            var prevTitleAndArtist = $('#prevTitleArtist');
            var nextTitleAndArtist = $('#nextTitleArtist');

            try{
                $(channelName).html(data['channel']['name']);
                if(channelName == null || channelName == '' || channelName == undefined || channelName == "null"){
                    $(channelName).html('Radiotystnad. Läskigt..');
                }
            }catch(err){
                console.log(err.message);
                $(channelName).html('Radiotystnad. Läskigt..');
            }
            try{
                $(titleAndArtist).html(data['song']['title']+' - '+data['song']['artist']);
                if(titleAndArtist == null || titleAndArtist == '' || titleAndArtist == undefined || titleAndArtist == "null"){
                    $(titleAndArtist).html('Okänd eller bara en massa snack?');
                }
            }catch(err){
                console.log(err.message);
                $(titleAndArtist).html('Okänd eller bara en massa snack?');
            }
            try{
                $(prevTitleAndArtist).html('Previous song <br>'+data['previoussong']['title']+ ' - '+data['previoussong']['artist']);
                if(prevTitleAndArtist == null || prevTitleAndArtist == '' || prevTitleAndArtist == undefined || prevTitleAndArtist == "null"){
                    $(prevTitleAndArtist).html('Previous song <br>Okänd eller bara en massa snack?');
                }
            }catch(err){
                console.log(err.message);
                $(prevTitleAndArtist).html('Previous song <br>Okänd eller bara en massa snack?');
            }
            try{
                $(nextTitleAndArtist).html('Next song <br>'+data['nextsong']['title']+ ' - '+data['nextsong']['artist']);
                if(nextTitleAndArtist == null || nextTitleAndArtist == '' || nextTitleAndArtist == undefined || nextTitleAndArtist == "null"){
                    $(nextTitleAndArtist).html('Next song <br>Okänd eller bara en massa snack?');
                }
            }catch(err){
                console.log(err.message);
                $(nextTitleAndArtist).html('Previous song <br>Okänd eller bara en massa snack?');
            }
            
            var spotifyPrev = $('.spotify-prev-iframe');
            var spotifyPresent = $('.spotify-present-iframe');
            var spotifyNext = $('.spotify-next-iframe');

            try{
                var previousSongSpotifyLink = data['previoussong']['spotifyLink'];
                $(spotifyPrev).attr('src', previousSongSpotifyLink);
                if(previousSongSpotifyLink == null || previousSongSpotifyLink == '' || previousSongSpotifyLink == undefined || previousSongSpotifyLink == "null"){
                    spotifyNotFound(spotifyPrev);
                }
            }catch(err){
                console.log(err.message);   
                spotifyNotFound(spotifyPrev);
            }
              try{
                var songSpotifyLink = data['song']['spotifyLink'];
                $(spotifyPresent).attr('src', songSpotifyLink);
                if(songSpotifyLink == null || songSpotifyLink == '' || songSpotifyLink == undefined || songSpotifyLink == "null"){
                    spotifyNotFound(spotifyPresent);
                }
            }catch(err){
                console.log(err.message);
                spotifyNotFound(spotifyPresent);
            }
            try{
                var nextSongSpotifyLink = data['nextsong']['spotifyLink'];
                $(spotifyNext).attr('src', nextSongSpotifyLink);
                if(nextSongSpotifyLink == null || nextSongSpotifyLink == '' || nextSongSpotifyLink == undefined || nextSongSpotifyLink == "null"){
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
                if(previousSongYoutubeLink == null || previousSongYoutubeLink == '' || previousSongYoutubeLink == undefined || previousSongYoutubeLink == "null"){
                    youtubeNotFound(youtubePrev);
                }
            }catch(err){
                console.log(err.message);
                youtubeNotFound(youtubePrev);
            }
             try{
                var songYoutubeLink = data['song']['youtubeLink'];
                $(youtubePresent).attr('src', songYoutubeLink);
                if(songYoutubeLink == null || songYoutubeLink == '' || songYoutubeLink == undefined || songYoutubeLink == "null"){
                    youtubeNotFound(youtubePresent);
                }
            }catch(err){
                console.log(err.message);
                youtubeNotFound(youtubePresent);   
            }
             try{
                var nextSongYoutubeLink = data['nextsong']['youtubeLink'];
                $(youtubeNext).attr('src', nextSongYoutubeLink);
                if(nextSongYoutubeLink == null || nextSongYoutubeLink == '' || nextSongYoutubeLink == undefined || nextSongYoutubeLink == "null"){
                    youtubeNotFound(youtubeNext);
                }
            }catch(err){
                console.log(err.message);
                youtubeNotFound(youtubeNext);
            }                
        }
    });
window.interval = setInterval(() => {
    refresh(id)}, 60000);
}

function myStopFunction() {
  clearInterval(window.interval);
}

function refresh(id){
     $.ajax({
        url: 'http://localhost:5000/v1/channels/'+id,
        type: "GET",
        dataType: "json",
        success: function (data) {

            var channelName = $('#radioName');
            var titleAndArtist = $('#songTitleArtist');
            var prevTitleAndArtist = $('#prevTitleArtist');
            var nextTitleAndArtist = $('#nextTitleArtist');

            try{
                $(channelName).html(data['channel']['name']);
                if(channelName == null || channelName == '' || channelName == undefined || channelName == "null"){
                    $(channelName).html('Radiotystnad. Läskigt..');
                }
            }catch(err){
                console.log(err.message);
                $(channelName).html('Radiotystnad. Läskigt..');
            }
            try{
                $(titleAndArtist).html(data['song']['title']+' - '+data['song']['artist']);
                if(titleAndArtist == null || titleAndArtist == '' || titleAndArtist == undefined || titleAndArtist == "null"){
                    $(titleAndArtist).html('Okänd eller bara en massa snack?');
                }
            }catch(err){
                console.log(err.message);
                $(titleAndArtist).html('Okänd eller bara en massa snack?');
            }
            try{
                $(prevTitleAndArtist).html('Previous song <br>'+data['previoussong']['title']+ ' - '+data['previoussong']['artist']);
                if(prevTitleAndArtist == null || prevTitleAndArtist == '' || prevTitleAndArtist == undefined || prevTitleAndArtist == "null"){
                    $(prevTitleAndArtist).html('Previous song <br>Okänd eller bara en massa snack?');
                }
            }catch(err){
                console.log(err.message);
                $(prevTitleAndArtist).html('Previous song <br>Okänd eller bara en massa snack?');
            }
            try{
                $(nextTitleAndArtist).html('Next song <br>'+data['nextsong']['title']+ ' - '+data['nextsong']['artist']);
                if(nextTitleAndArtist == null || nextTitleAndArtist == '' || nextTitleAndArtist == undefined || nextTitleAndArtist == "null"){
                    $(nextTitleAndArtist).html('Next song <br>Okänd eller bara en massa snack?');
                }
            }catch(err){
                console.log(err.message);
                $(nextTitleAndArtist).html('Previous song <br>Okänd eller bara en massa snack?');
            }
            
            var spotifyPrev = $('.spotify-prev-iframe');
            var spotifyPresent = $('.spotify-present-iframe');
            var spotifyNext = $('.spotify-next-iframe');

            try{
                var previousSongSpotifyLink = data['previoussong']['spotifyLink'];
                $(spotifyPrev).attr('src', previousSongSpotifyLink);
                if(previousSongSpotifyLink == null || previousSongSpotifyLink == '' || previousSongSpotifyLink == undefined || previousSongSpotifyLink == "null"){
                    spotifyNotFound(spotifyPrev);
                }
            }catch(err){
                console.log(err.message);   
                spotifyNotFound(spotifyPrev);
            }
              try{
                var songSpotifyLink = data['song']['spotifyLink'];
                $(spotifyPresent).attr('src', songSpotifyLink);
                if(songSpotifyLink == null || songSpotifyLink == '' || songSpotifyLink == undefined || songSpotifyLink == "null"){
                    spotifyNotFound(spotifyPresent);
                }
            }catch(err){
                console.log(err.message);
                spotifyNotFound(spotifyPresent);
            }
            try{
                var nextSongSpotifyLink = data['nextsong']['spotifyLink'];
                $(spotifyNext).attr('src', nextSongSpotifyLink);
                if(nextSongSpotifyLink == null || nextSongSpotifyLink == '' || nextSongSpotifyLink == undefined || nextSongSpotifyLink == "null"){
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
                if(previousSongYoutubeLink == null || previousSongYoutubeLink == '' || previousSongYoutubeLink == undefined || previousSongYoutubeLink == "null"){
                    youtubeNotFound(youtubePrev);
                }
            }catch(err){
                console.log(err.message);
                youtubeNotFound(youtubePrev);
            }
             try{
                var songYoutubeLink = data['song']['youtubeLink'];
                $(youtubePresent).attr('src', songYoutubeLink);
                if(songYoutubeLink == null || songYoutubeLink == '' || songYoutubeLink == undefined || songYoutubeLink == "null"){
                    youtubeNotFound(youtubePresent);
                }
            }catch(err){
                console.log(err.message);
                youtubeNotFound(youtubePresent);   
            }
             try{
                var nextSongYoutubeLink = data['nextsong']['youtubeLink'];
                $(youtubeNext).attr('src', nextSongYoutubeLink);
                if(nextSongYoutubeLink == null || nextSongYoutubeLink == '' || nextSongYoutubeLink == undefined || nextSongYoutubeLink == "null"){
                    youtubeNotFound(youtubeNext);
                }
            }catch(err){
                console.log(err.message);
                youtubeNotFound(youtubeNext);
            }                
        }
    });

}