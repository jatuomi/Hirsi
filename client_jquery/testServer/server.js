var http = require('http');
var express = require('express');
var app = express();

app.use(express.static('../app'));

var theWord = 'SALASANA';

var server = app.listen(9000);

app.get('/HirsiServer_JAX-RS/services/words/getWord', function (req, res)
{
    res.json( { id: '5', length: theWord.length } );
})

app.get('/HirsiServer_JAX-RS/services/words/letterInWord/:id', function (req, res)
{
    var id = req.params.id;
    var letter = req.query.letter;

    var jsonData = {};

    for( var i = 0; i < theWord.length; i++ )
    {
        if( theWord[i] == letter )
        {
            jsonData[i] = letter;  
        }
    }

    res.json( jsonData );
})
