#pragma strict

var skin : GUISkin;

function OnGUI()
{
	GUI.skin = skin;
	
	var sw : int = Screen.width;
	var sh : int = Screen.height;
	
	GUI.Label(Rect(0, sh/4, sw, sh/4), "가짜로 만들어진 세상에서 살고 있었음을\n 알게된 트루먼, 장애물들을 피해 밖으로\n 나가는 문을 찾자", "Lines");
	GUI.Label(Rect(0, sh/2, sw, sh/4), "Click to Start", "Info");
}

function Start () {

}

function Update () {

	if(Input.GetMouseButtonDown(0))
	{
		Application.LoadLevel("scene1");
	}

}