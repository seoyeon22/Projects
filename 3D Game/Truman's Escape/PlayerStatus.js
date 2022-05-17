#pragma strict

private var life : int = 100;
private var effectFlag : boolean;
private var originalLifeColor : Color;
private var lifeStyle : GUIStyle;

var coinParticlePrefab : GameObject;
var damageParticlePrefab : GameObject;
var skin : GUISkin;
var dead : boolean = false;
var deadMessage : String = "Game Over";

/*
function CatchCoin(amount : int)
{
	Instantiate(coinParticlePrefab, transform.position, transform.rotation);
	score += amount;
}
*/

function ApplyDamage(amount : int)
{
	effectFlag = true;
	yield WaitForSeconds(0.3);
	effectFlag = false;
	lifeStyle.normal.textColor = originalLifeColor;
		
	life -= amount;
	if(life <= 0)
	{
		gameObject.SendMessageUpwards("Death");
		animation.Play("Rig|man_fall");
		dead = true;
	}
}

function OnGUI()
{
	var sw : int = Screen.width;
	var sh : int = Screen.height;
	
	GUI.skin = skin;
	var rect1 : Rect = Rect(0, 0, Screen.width, Screen.height);
	var rect2 : Rect = Rect(Screen.width/2, 0, Screen.width, Screen.height);
	GUI.Label(rect1, "LIFE: " + life.ToString(), "Life");
	if(dead)
	{
		GUI.Label(new Rect(0, sh/2, sw, sh/4), deadMessage, "GameOver");
	}
	
}

function Start () 
{
	dead = false;
	lifeStyle = skin.GetStyle("Life");
	originalLifeColor = Color.green;
	effectFlag = false;
}

function Update () 
{
	if(effectFlag)
		lifeStyle.normal.textColor = Color.red * Mathf.Abs(Mathf.Sin(40.0 * Time.time));
}