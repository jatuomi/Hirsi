"use strict";

$( document ).ready(function() {

	var alphapets = 'ABCDEFGHIJKLMNOPQRSTUVXYZÅÄÖ'.split('');

	var theWord = "TELEFOONI";
	var maxErrors = 6;
	var errorCount = 0;

	$('#gameFinished').hide();


	for(var i = 0; i < alphapets.length; i++)
	{
		var button = $('<button></button>').text(alphapets[i]);

		button.attr('id','letter' + alphapets[i]);
		button.attr('class','letter');

		button.click((function(btn, letter){
			return function()
			{
				if(theWord.indexOf(letter) === -1)
				{
					btn.css('color','red');
					errorCount++;

					if( errorCount > maxErrors )
					{
						console.log("peli päättyi tappioon");

						$('#gameFinished').append($('<b></b>').text('Aijai, hävisit pelin!'));
						$('#gameFinished').append($('<br>'));

						var newGamebutton = $('<button></button>').text('Yritä uudelleen');

						newGamebutton.click(function(){
							document.location.reload(true);
						});

						$('#gameFinished').append(newGamebutton);
						$('#gameFinished').show(1000);
					}
				}
				else
				{
					btn.css('color','green');

					theWord.split('').forEach(function(element, index, array){
						if(element == letter)
						{
							console.log('Letter ' + letter + ' found from position ' + index);
							$('#hiddenLetter' + index).text(' ' + letter + ' ');
							$('#hiddenLetter' + index).attr('data-hidden', false);
						}
					});

					if( $('.hiddenLetter[data-hidden=true]').length == 0 )
					{
						console.log("peli päättyi ok");

						$('#gameFinished').append($('<b></b>').text('Onneksi olkoon, voitit pelin!'));
						$('#gameFinished').append($('<br>'));
						$('#gameFinished').append($('<p></p>').text('Teit yhteensä ' + errorCount + ' virheellistä valintaa.'));
						$('#gameFinished').append($('<br>'));

						var newGamebutton = $('<button></button>').text('Aloita uusi peli');

						newGamebutton.click(function(){
							document.location.reload(true);
						});
						
						$('#gameFinished').append(newGamebutton);
						$('#gameFinished').show(1000);
					}
				}

				btn.attr('disabled', true);
			}
		})(button, alphapets[i]));

		$('#inputLetters').append(button);
	}


	for(var i = 0; i < theWord.length; i++)
	{
		var hiddenLetter = $('<b></b>', {id: "hiddenLetter" + i, class: "hiddenLetter" } ).text(" _ ");
		hiddenLetter.attr('data-hidden', true);

		$('#hiddenLetters').append(hiddenLetter);
	}

});
