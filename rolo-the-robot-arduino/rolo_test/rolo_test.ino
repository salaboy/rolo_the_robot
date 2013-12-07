
#include <Wire.h>
#include <NXTShield.h>
#include <PID.h>
#include <MsTimer2.h>
#include <NewPing.h>

#define TRIGGER_PIN_FRONT  44
#define ECHO_PIN_FRONT     45
#define TRIGGER_PIN_BACK  46
#define ECHO_PIN_BACK     47
#define TRIGGER_PIN_LEFT  40
#define ECHO_PIN_LEFT     41
#define TRIGGER_PIN_RIGHT  42
#define ECHO_PIN_RIGHT     43
#define MAX_DISTANCE 200

Motor1 leftMotor;
Motor2 rightMotor;

NewPing sonar_front(TRIGGER_PIN_FRONT, ECHO_PIN_FRONT, MAX_DISTANCE);

NewPing sonar_back(TRIGGER_PIN_BACK, ECHO_PIN_BACK, MAX_DISTANCE);

NewPing sonar_right(TRIGGER_PIN_RIGHT, ECHO_PIN_RIGHT, MAX_DISTANCE);

NewPing sonar_left(TRIGGER_PIN_LEFT, ECHO_PIN_LEFT, MAX_DISTANCE);


//Define Variables we'll be connecting to
double Setpoint_lm, Setpoint_rm, Input_lm, Input_rm, Output_lm, Output_rm;

//Define the aggressive and conservative Tuning Parameters
double aggKp_lm=70, aggKi_lm=15, aggKd_lm=0.005;  // Aggressive
double consKp_lm=50, consKi_lm=8, consKd_lm=0;    // Conservative
double aggKp_rm=70, aggKi_rm=15, aggKd_rm=0.005;  // Aggressive
double consKp_rm=50, consKi_rm=8, consKd_rm=0;    // Conservative
 
//Specify the links and initial tuning parameters
PID myPID_lm(&Input_lm, &Output_lm, &Setpoint_lm, consKp_lm, consKi_lm, consKd_lm, DIRECT);
PID myPID_rm(&Input_rm, &Output_rm, &Setpoint_rm, consKp_rm, consKi_rm, consKd_rm, DIRECT);


//Commands Format
// Command Device
// Command Name
// Command Parameters Size
// Command Parameters

// Command Example:
// left-motor:start:4:forward:255:90:brake;
// left-motor:read:0;

//Devices name:
// - front-wheels
// - sonar1
// - left-motor
// - rightMotor

String inputString = "";         // a string to hold incoming data
boolean stringComplete = false;  // whether the string is complete
int leftMotorSpeed = 255;
int rightMotorSpeed = 255;
String currentDirectionLeft = "forward";
String currentDirectionRight = "forward";

float Diam = 43.2 ;// Diametro de las ruedas en mm
int Dist = 166;    // Separacion entre ruedas en mm
float val = 0.00;
int pot_lm = 120;
int pot_rm = 120;
int lm_old = 0;
int rm_old = 0;
int lm_new = 0;
int rm_new = 0;
int desp_lm = 0;
int desp_rm = 0;
int pul_seg_lm = 0;
int pul_seg_rm = 0;



void setup() {
  // initialize serial:
  Serial.begin(115200);
  // reserve 200 bytes for the inputString:
  inputString.reserve(200);
  
  MsTimer2::set(60, velo); //este 60, no se si son 60 milisegundos o microsegundos ja, se me ha hecho un lio
  MsTimer2::start();
  
  Setpoint_lm = 35;  //Cantidad de pulsos leidos entre cada llamada a "velo"
  Setpoint_rm = 35;  // "lm" = left Motor ; "rm" = right Motor
                     // es el valor que el algoritmo intentara mantener corrigiendo los errores
//turn the PID on

  myPID_lm.SetMode(AUTOMATIC);
  myPID_lm.SetOutputLimits(0, 255);
  myPID_rm.SetMode(AUTOMATIC);
  myPID_rm.SetOutputLimits(0, 255);
}

void loop() {
  // print the string when a newline arrives:
  
  
  if (stringComplete) {
    int deviceNameDelimiter = inputString.indexOf(':');
    String deviceName = inputString.substring(0, deviceNameDelimiter);
    String command = inputString.substring(deviceNameDelimiter+1, inputString.length());
    int commandNameDelimiter = command.indexOf(':');
    String commandName = command.substring(0, commandNameDelimiter);
    String arguments = command.substring(commandNameDelimiter+1, command.length());
    int nroOfArgsDelimiter = arguments.indexOf(':');
    int numberOfArguments = arguments.substring(0, nroOfArgsDelimiter).toInt();
    String cleanArguments = arguments.substring(nroOfArgsDelimiter+1, arguments.length());
//    Serial.println("Device Name: '"+deviceName+"'");
//    Serial.println("Command Name: '"+commandName+"'");
//    Serial.println("Arguments: '"+arguments+"'");
//    Serial.println("Nro of Arguments: '"+String(numberOfArguments)+"'");
//    Serial.println("Clean Arguments: "+cleanArguments);
    
    String args[numberOfArguments];
    for(int i=0; i < numberOfArguments; i ++){
     // Serial.println("Args now: "+cleanArguments);
      int currentArgDelimiter = cleanArguments.indexOf(':');
      String currentArg = cleanArguments.substring(0, currentArgDelimiter);
      args[i] = currentArg;
    //  Serial.println("Arg ["+String(i)+"]: '"+args[i]+"'");
      cleanArguments = cleanArguments.substring(currentArgDelimiter+1, cleanArguments.length());  
    }
  
    commandName.trim();
    // clear the string before continue
    inputString = "";
    stringComplete = false;
    
  
      
      
      //front-wheels:rotate:2:left:90;
      if(deviceName=="front-wheels"){
        if(commandName=="rotate"){
           rotate_front_wheels( args[0], args[1].toInt());
        }else if(commandName=="move"){
           move_front_wheels( args[0], args[1].toInt());
        }if(commandName=="rotate-move"){
           rotate_front_wheels( args[0], args[1].toInt());
           if(args[2] == "forward"){
             move_single_motor("right-motor", forward );
             move_single_motor("left-motor", forward );
           }else{
             move_single_motor("right-motor", backward );
             move_single_motor("left-motor", backward );
           }
        }else if(commandName=="setDiam"){
           Diam = (float)args[0].toInt();
        }else if(commandName=="setDist"){
           Dist = args[0].toInt();
        }else if(commandName=="stop-all"){
           stop_single_motor("right-motor");
           stop_single_motor("left-motor");
        }else if(commandName=="forward"){
           move_single_motor("right-motor", forward);
           move_single_motor("left-motor", forward);
        }else if(commandName=="backward"){
           move_single_motor("right-motor", backward);
           move_single_motor("left-motor", backward);
        }
      }else if(deviceName=="sonars"){
        if(commandName=="read"){
          int uSfront = sonar_front.ping_median(10);
          int uSright = sonar_right.ping_median(10);
          int uSleft = sonar_left.ping_median(10);
          int uSback = sonar_back.ping_median(10);
          int front = uSfront / US_ROUNDTRIP_CM;
          int right = uSright / US_ROUNDTRIP_CM;
          int left = uSleft / US_ROUNDTRIP_CM;
          int back = uSback / US_ROUNDTRIP_CM;
          String report = String("SONARS_REPORT:");
                report.concat(front);
                report.concat("-");
                report.concat(right);
                report.concat("-");
                report.concat(left);
                report.concat("-");
                report.concat(back);
               report.concat(";");
          Serial.print(report);
        }
      }
  
      
   // else{
       //left-motor:rotate:2:90:forward;
//       if(commandName=="rotate"){
//              Direction mydirection = forward;
//              if( args[1].equals("backward")){
//                 mydirection = backward;
//              }
//              Brake mybrake = brake;
//              if( args[2].equals("coast")){
//                mybrake = coast;
//              }
//              move_single_motor(deviceName, mydirection, uint8_t(args[0].toInt()), mybrake);
//              while(is_turning_single_motor(deviceName));
//        }else if(commandName=="stop"){
//            stop_single_motor(deviceName);
//        }else if(commandName=="read"){
//            Serial.print("ANGLE_REPORT:");
//            Serial.print(read_position_single_motor(deviceName),DEC);
//            Serial.print(";");
//        }else if(commandName=="reset"){
//            reset_position_single_motor(deviceName);
//        }else if(commandName=="isturning"){
//            Serial.print("STATUS_REPORT:");
//            Serial.print(is_turning_single_motor(deviceName)+";");
//        }else if(commandName=="forward"){
//            move_single_motor(deviceName, forward);
//            if(deviceName.startsWith("left")){
//                currentDirectionLeft = "forward";
//            }else{
//                currentDirectionRight = "forward";
//            }
//                        
//        }else if(commandName=="backward"){
//            move_single_motor(deviceName, backward);
//            if(deviceName.startsWith("left")){
//                currentDirectionLeft = "backward";
//            }else{
//                currentDirectionRight = "backward";
//            }
//        }else if(commandName=="setSpeed"){
//           
//            if(is_turning_single_motor(deviceName)){
//              
//             if(deviceName.startsWith("left")){ 
//                if(currentDirectionLeft == "forward"){
//                   move_single_motor(deviceName, forward);
//                }else if( currentDirectionLeft == "backward"){
//                   move_single_motor(deviceName, backward);
//                }
//             }else{
//                if(deviceName.startsWith("left")){ 
//                  if(currentDirectionRight == "forward"){
//                     move_single_motor(deviceName, forward);
//                  }else if( currentDirectionRight == "backward"){
//                     move_single_motor(deviceName, backward);
//                  }
//                }
//             }
//              
//              
//            }
//        }else if(commandName=="getSpeed"){
//            if(deviceName.startsWith("left")){
//              Serial.print("SPEED_REPORT:"+String(leftMotorSpeed)+";");
//            }else{
//              Serial.print("SPEED_REPORT:"+String(rightMotorSpeed)+";");
//            }
//        }
//    }

  }else{
  
  
    
    int uSfront = sonar_front.ping_median(10);
            int uSright = sonar_right.ping_median(10);
            int uSleft = sonar_left.ping_median(10);
            int uSback = sonar_back.ping_median(10);
            int front = uSfront / US_ROUNDTRIP_CM;
            int right = uSright / US_ROUNDTRIP_CM;
            int left = uSleft / US_ROUNDTRIP_CM;
            int back = uSback / US_ROUNDTRIP_CM;
            String report = String("SONARS_REPORT:");
                  report.concat(front);
                  report.concat("-");
                  report.concat(right);
                  report.concat("-");
                  report.concat(left);
                  report.concat("-");
                  report.concat(back);
                 report.concat(";");
            Serial.print(report);
            delay(200);
  }
  
}




void move_front_wheels(String dir, float distance)
{
  Input_lm = 0;
  Input_rm = 0;
  desp_lm = (360 * distance) / (Diam * PI); 
  desp_rm = (360 * distance) / (Diam * PI); 

  leftMotor.resetPosition();
  rightMotor.resetPosition();
  
   Direction mydirection = forward;
   if( dir.equals("backward")){
        mydirection = backward;
   }
  
  leftMotor.move(mydirection, pot_lm, desp_lm, brake); // en este momento el valor de pot_lm y pot_rm, es 120,
  rightMotor.move(mydirection, pot_rm, desp_rm, brake);// que es el valor que se me ocurrio poner, cuando inicialice las
                                                    // las variables pero habria que probar otros valores iniciales
  while(leftMotor.isTurning())                      
    {
      double gap_lm = abs(Setpoint_lm - Input_lm); //distance away from setpoint motor Izquierdo
      if(gap_lm<10)                              //we're close to setpoint, use conservative tuning parameters
       {  
         myPID_lm.SetTunings(consKp_lm, consKi_lm, consKd_lm);  //todo este "while" y el que sigue no se si funciona
       }                                                        // o si funcionaria mejor puesto dentro de la
      else                                                      //funcion "velo"
       {
         myPID_lm.SetTunings(aggKp_lm, aggKi_lm, aggKd_lm);
       }
    }  
   
    while(rightMotor.isTurning())
    {
       double gap_rm = abs(Setpoint_rm - Input_rm); 
       if(gap_rm<10)                              
       {  
         myPID_rm.SetTunings(consKp_rm, consKi_rm, consKd_rm);
       }
      else 
       {
         myPID_rm.SetTunings(aggKp_rm, aggKi_rm, aggKd_rm);
       } 
    }
}


void rotate_front_wheels(String dir, float ang)
{
   Input_lm = 0;
   Input_rm = 0;
   leftMotor.resetPosition();
   rightMotor.resetPosition();
   desp_lm = 360 *(Dist * PI / 360 * ang) / (Diam * PI);
   desp_rm = 360 *(Dist * PI / 360 * ang) / (Diam * PI);  
   if(dir == "right"){
     leftMotor.move(forward, pot_lm, desp_lm, brake); 
     rightMotor.move(backward, pot_rm, desp_rm, brake);
   }else if(dir == "left"){
     rightMotor.move(forward, pot_lm, desp_lm, brake); 
     leftMotor.move(backward, pot_rm, desp_rm, brake);
   }
   while(leftMotor.isTurning())
    {
      double gap_lm = abs(Setpoint_lm-Input_lm); //distance away from setpoint motor Izquierdo
      if(gap_lm<10)                              //we're close to setpoint, use conservative tuning parameters
       {  
         myPID_lm.SetTunings(consKp_lm, consKi_lm, consKd_lm);
       }
      else  
       {
         myPID_lm.SetTunings(aggKp_lm, aggKi_lm, aggKd_lm);
       }
    }  
   
    while(rightMotor.isTurning())
    {
       double gap_rm = abs(Setpoint_rm-Input_rm); //distance away from setpoint motor Derecho
       if(gap_rm<10)                             //we're close to setpoint, use conservative tuning parameters
       {  
         myPID_rm.SetTunings(consKp_rm, consKi_rm, consKd_rm);
       }
      else 
       {
         myPID_rm.SetTunings(aggKp_rm, aggKi_rm, aggKd_rm);
       } 
    }
} 


void velo()
{
 
  lm_old = lm_new;
  lm_new = leftMotor.readPosition();
  rm_old = rm_new;
  rm_new = rightMotor.readPosition();
  pul_seg_lm = abs(lm_new - lm_old);
  pul_seg_rm = abs(rm_new - rm_old);
  Input_lm = pul_seg_lm;
  Input_rm = pul_seg_rm;
  myPID_lm.Compute(); //realiza el calculo para la correccion del error y el resultado que es un valor entre 0 y 255
  myPID_rm.Compute(); //necesario para corregir la velocidad lo pone en las variables Output_lm y Output_rm
  analogWrite(9,Output_lm ); // reescribo directamente en el pin PWM (4) del motor izquierdo para cambiar la potencia
  analogWrite(10,Output_rm);  // reescribo directamente en el pin PWM (7) del motor derecho para cambiar la potencia
}



// FIXING NXT SHIELD sh1t
void move_single_motor(String motorName, Direction direction, int rotation, Brake brake){
   if(motorName.startsWith("left")){
      leftMotor.move(direction, leftMotorSpeed, rotation, brake );
   } else {
      rightMotor.move(direction, rightMotorSpeed, rotation, brake );
   }
} 

void move_single_motor(String motorName, Direction direction){
   if(motorName.startsWith("left")){
      leftMotor.move( direction, leftMotorSpeed );
   } else {
      rightMotor.move( direction, rightMotorSpeed );
   }
} 

bool is_turning_single_motor(String motorName){
  if(motorName.startsWith("left")){
      return leftMotor.isTurning();
   } else {
      return rightMotor.isTurning();
   }
}

void stop_single_motor(String motorName){
  if(motorName.startsWith("left")){
      leftMotor.stop();
   } else {
      rightMotor.stop();
   }
}

long read_position_single_motor(String motorName){
  if(motorName.startsWith("left")){
      return leftMotor.readPosition();
   } else {
      return rightMotor.readPosition();
   }
}

void reset_position_single_motor(String motorName){
  if(motorName.startsWith("left")){
      leftMotor.resetPosition();
   } else {
      rightMotor.resetPosition();
   }
}

/*
  SerialEvent occurs whenever a new data comes in the
 hardware serial RX.  This routine is run between each
 time loop() runs, so using delay inside loop can delay
 response.  Multiple bytes of data may be available.
 */
void serialEvent() {
  while (Serial.available()) {
    // get the new byte:
    char inChar = (char)Serial.read(); 
    // add it to the inputString:
    inputString += inChar;
    // if the incoming character is a newline, set a flag
    // so the main loop can do something about it:
    if (inChar == ';') {
      inputString = inputString.substring(0,inputString.length() -1);
      stringComplete = true;
    } 
  }
}
