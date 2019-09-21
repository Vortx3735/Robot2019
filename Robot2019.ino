/*
  The range readings are in units of inches. */

#include <Wire.h>
#include <VL53L0X.h>


VL53L0X sensor;

void setup()
{
  Serial.begin(9600);
  Wire.begin();

  sensor.init();

  // Start continuous back-to-back mode (take readings as
  // fast as possible).  To use continuous timed mode
  // instead, provide a desired inter-measurement period in
  // ms (e.g. sensor.startContinuous(100)).

  sensor.startContinuous();
}

void loop()
{
  String output = String(sensor.readRangeContinuousMillimeters() / 25.4 , 2);
  Serial.println(pad(output, 6));

}
String reverse(String in) {
  String out = "";
  for (int i = (in.length() - 1); i >= 0; i--) {
    out += in.charAt(i);
  }
  return out;
}
String pad(String in, int vsize) {
  String temp = reverse(in);
  for (int i = 0 ; i < (vsize - in.length()); i++) {
    temp += "0";
  }
  return reverse(temp);
}
