#pragma strict

var message : String = "This is a wall";
var displayTime : float = 2.0;
var display : boolean = false;
var skin : GUISkin;

function OnTriggerEnter(other: Collider)
{
	displayTime = 2.0;
	display = true;
}

function OnGUI()
{	

	GUI.skin = skin;
	
	if(display)
	{
		var sw : int = Screen.width;
		var sh : int = Screen.height;
	
		GUI.color = Color.blue;
		GUI.Label(new Rect(0, sh/2, sw, sh/4), message, "Message");	
	}
}

function Start () {

}

function Update () 
{
	if(display){
		displayTime -= Time.deltaTime;
	}
	
	if(displayTime <= 0.0)
	{
		display = false;
	}	

}