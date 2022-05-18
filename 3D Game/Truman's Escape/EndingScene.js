#pragma strict

var skin : GUISkin;

function OnGUI()
{
	GUI.skin = skin;
	
	var sw : int = Screen.width;
	var sh : int = Screen.height;
	
	GUI.Label(Rect(0, sh/4, sw, sh/4), "In case I don't see ya, \nGood afternoon, Good evening \nand Good night!", "Lines");
	GUI.Label(Rect(0, sh/2, sw, sh/4), "Click to Exit", "Info");
}

function Start () {

}

function Update () {
	
	if(Input.GetMouseButtonDown(0))
	{
		Application.Quit();
	}

}