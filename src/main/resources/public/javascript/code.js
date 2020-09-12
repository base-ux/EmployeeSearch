$(".carousel").carousel({
  interval: 10000
})



var curve = $(".welcomeLeftBarCurve")
var upperCurve = curve.getContext("2d");
upperCurve.fillStyle = "#FF0000";
upperCurve.fillRect(0,0,150,75);