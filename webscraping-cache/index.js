var request = require("request");
var cheerio = require("cheerio");
var express = require('express');
var app = express();
var http = require('http').Server(app);
var bodyParser = require('body-parser');
// var phantom=require('node-phantom');

app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({ extended: true })); // support encoded bodies

var googleUrl = "https://www.google.ca";

app.get('/', function (req, res) {
    res.send('Hello World!');
});
app.use('/home', express.static('index.html'));
app.use(express.static(__dirname + '/'));

http.listen(3000, function () {
    console.log('Example app listening on port 3000!');
});

app.post('/productList', function(req, res) {
	// console.log(req.body.name);
 //  	console.log(req.body.amount);
 	console.log(req.body);
 	returnArr = [];
 	console.log (googleUrl + "/#q=" + req.body.product.replace(" ", "+"));

 	var headers = { 
 		'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
 		'cookie': "S=travel-flights=m0nf_jI6VMlP3B1yMAIFig; OGPC=5062145-9:5062147-11:; SID=fgMH59tRo6GiIDoaQpvC6CkHCcaQuCIhL_Tp17qltr5zS8mM5ZPu8RpbLZIXnqza7PumEA.; HSID=AxAWzMzERhEff3K8v; SSID=Ar5iudSFKw202D6_7; APISID=J0s5iBSfK6E9QJpd/A8-0xBAc-oG5-VRMC; SAPISID=NQKMZSMgJDhEamlo/AmKhHFqWbMt1yvPnx; NID=82=fRMIZOMeScPSvLtBJbtUlxTL0p3DL6Fklr-PhAx30zPUdrbbN1Tu6ELoVG0iUjCXKs-COakFKOGvPaG1PQWY8FjnaLqSbRnSKV05KKuPbeRb2ORCzSsxPgfKc7T7LLIG2UV7A2nosOQe6nHUEm24dLxF2cEiKjDqRYsxaxbuhNSUIorJQTY1UHpurpsZNsW4_A7v9QG4zQX1kXhYqScYoVdeC8iuYTffxXOzgDlG_XXyX0LJbeNWv7xi9ialad15A57dvNaTNiXB-oYN0B4SdTbokwrma4qC; DV=0qd1GR2nDUEWaKqnwyR-xRtB3TzeqwI",
    	'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:24.0) Gecko/20100101 Firefox/24.0',
    	'Content-Type' : 'application/x-www-form-urlencoded' 
	};

	request.get({ url: googleUrl + "/#q=" + req.body.product.replace(" ", "+"), headers: headers }, function (e, r, body) {
    	console.log(body);
	});
	
 //    $(".r > a").each(function (i, element) {
	// 	console.log("hi");
	// 	var link = $(this).attr("data-href");
	// 	console.log(link);
	// 	request(googleUrl + req.body.product, function (error, response, body) {
	// 	  if (!error) {
	// 		var $ = cheerio.load(body);
	// 		var price = -1;
	// 		$(":contains('$')").each (function (i, element) {
	// 			var className = $(this).className;
	// 			if (className.contains("amount") ||
	// 				className.contains("amt") ||
	// 				className.contains("price") ||
	// 				className.contains("prc") ||
	// 				className.contains("amount")) {
	// 				try {
	// 					price = parseFloat($(this).text().replace(/[^\d.-]/g, ''));
	// 				} catch (err) {

	// 				}
	// 			}
	// 		});
	// 		if (price != -1) {
	// 			var matches = link.match(/^https?\:\/\/([^\/?#]+)(?:[\/?#]|$)/i);
	// 			var domain = matches && matches[1];  // domain will be null if no match is found
	// 			if (domain != null) {
	// 				returnArr.push([domain, price]);
	// 			}
	// 		}
	// 	  } else {
	// 	  	console.log("We’ve encountered an error: " + error);
	// 	  }
	// 	});
	// });
	// request(googleUrl + "/#q=" + req.body.product.replace(" ", "+"), function (error, response, body) {
	//   if (!error) {
		
	// 	} else {
	//     	console.log("We’ve encountered an error: " + error);
	// 	}
	// });
});

// The ajax request to test it

// $.ajax({
// 		            url: "http://localhost:3000/productList",
// 		            type: "post",
// 		            data: {product: "nexus 5"}
// 		          })
// .success(function (data) {
// console.log(data);
// });