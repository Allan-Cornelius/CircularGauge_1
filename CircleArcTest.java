//Componenta optimizata pentru afisare dinamica


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CircleArcTest extends Application{

	double GLOW_LEVEL;
	boolean HIGH_LIMIT_GLOW = false;
	boolean LOW_LIMIT_GLOW = true;
	
	@Override
	public void start(Stage primaryStage) throws Exception {

	primaryStage.setTitle("Circle Arc Example");      	  
	Pane root = new Pane(); 
		 
	
	
	//Arcul de cerc de baza peste care se suprapune arcul animat
	Arc arcBase = new Arc(150, 150, 100, 100, 0, 360);
	arcBase.setFill(Color.TRANSPARENT);
	arcBase.setType(ArcType.OPEN);
	arcBase.setStroke(Color.rgb(39,44,50));  //culoare background arc de cerc
	arcBase.setStrokeWidth(5);
	
	
	Glow glow = new Glow(); //efect de glow pentru arcul de cerc
	glow.setLevel(GLOW_LEVEL);
	
	//Arcul animat care are aplicat efectul de Glow
	Arc arc = new Arc(150, 150, 100, 100, 0, 0);
	arc.setFill(Color.TRANSPARENT);
	arc.setType(ArcType.OPEN);
	//arc.setStroke(Color.rgb(77,208,225));   //culoare arc de cerc
	//arc.setStroke(Color.rgb(255,183,77));   //culoare arc de cerc
	//arc.setStroke(Color.rgb(129,199,132));   //culoare arc de cerc
	//arc.setStroke(Color.rgb(149,117,205));   //culoare arc de cerc
	//arc.setStroke(Color.rgb(186,104,200));   //culoare arc de cerc
	//arc.setStroke(Color.rgb(229,115,115));   //culoare arc de cerc
	arc.setStroke(Color.rgb(0,255,64));   //culoare arc de cerc verde
	arc.setStrokeWidth(5);
	arc.setStyle("-fx-opacity:1.0;");  //setare opacitate 0.0-0.1
	arc.setStyle("-fx-stroke-line-cap:round;");  //capetele arcului sunt rotunde
	arc.setEffect(glow);  //apelare metoda de glow pentru arcul de cerc
	
	//text pentru valoarea variabilei
	Text text = new Text(120f, 170f, "5201");
	text.setFill(Color.LIGHTGRAY);
	text.setFont(Font.font("CalibriLight",FontWeight.EXTRA_LIGHT,FontPosture.REGULAR,60));
	text.setEffect(glow);
	
	//text pentru unitatea de masura
	Text textUM = new Text(140f, 210f, "%");
	textUM.setFill(Color.LIGHTGRAY);
	textUM.setFont(Font.font("CalibriLight",FontWeight.EXTRA_LIGHT,FontPosture.REGULAR,30));
	textUM.setEffect(glow);
	

	EventHandler<ActionEvent> eventhandler = e->{
		System.out.println((float) GLOW_LEVEL);
		
		if(GLOW_LEVEL > 0.9) {HIGH_LIMIT_GLOW = true; LOW_LIMIT_GLOW = false;}
		if(GLOW_LEVEL < 0.2) {LOW_LIMIT_GLOW = true; HIGH_LIMIT_GLOW = false;}
		
		if(HIGH_LIMIT_GLOW) {GLOW_LEVEL = GLOW_LEVEL - 0.1;}
		if(LOW_LIMIT_GLOW) {GLOW_LEVEL = GLOW_LEVEL + 0.1;}
		

		glow.setLevel(GLOW_LEVEL);
		int glowLevelFloat = (int) (100*GLOW_LEVEL);
		text.setText(Integer.toString(glowLevelFloat));  //afisare valoare variabila glow in componenta
		arc.setLength(GLOW_LEVEL*360);  //variatie lungime arc si glow simultan
		
	};
	
	
	
	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), eventhandler));
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.play();
	
	root.getChildren().addAll(arcBase,arc,text,textUM);
	Scene scene = new Scene (root,300,300,Color.BLACK); 
	primaryStage.setScene(scene);  
	primaryStage.show();    
	}
	
	public static void main(String[] args) {
		Application.launch();
	}
}

