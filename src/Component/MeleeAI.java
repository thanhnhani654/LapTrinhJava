/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;
import GameObject.Enemy;
import GameObject.Player;
import Utility.Int2;
import java.math.*;

/**
 *
 * @author Nhan
 */
public class MeleeAI {
    Enemy enemy;
    Int2 playerPos;
    Int2 distance;
    AttackComponent attackCom;
    //Movement movement;
    public MeleeAI(Enemy enemy)
    {
        this.enemy = enemy;
        attackCom =new AttackComponent(enemy);
        playerPos = new Int2(0,0);
        distance = new Int2(0,0);
        //movement = new Movement(enemy);
    }
    
    public void Update()
    {
        //attack
        attackCom.DoMeleeAttack();
        if (attackCom.attackEnd)
            TurnBaseSystem.getInstance().nextKey();
        playerPos.x = Player.getInst().hero.getPosition().x;
        playerPos.y = Player.getInst().hero.getPosition().y;
        distance.x = enemy.getPosition().x - playerPos.x;
        distance.y = enemy.getPosition().y - playerPos.y;
        
        if (enemy.turnbase.getKeyValue(enemy.key))
        {
            Int2 temp = new Int2(0,0);
            temp.x = enemy.getPosition().x;
            temp.y = enemy.getPosition().y;
            temp.x+=1;
            
            //movement.move(temp);
            if(enemy.movement.move(this.move()))
                enemy.turnbase.nextKey();
            else
            {
                if ((Math.abs(distance.x) == 1 && Math.abs(distance.y) ==0)|| (Math.abs(distance.x) == 0 && Math.abs(distance.y) == 1))
                {
                    attackCom.getTarget(Player.getInst().hero);
                    attackCom.meleeAttack();
                }
            }
        }
    }
    
    public Int2 move()
    {
        if ((Math.abs(distance.x) == 0 && Math.abs(distance.y) <= 1) || (Math.abs(distance.x) <= 1 && Math.abs(distance.y) == 0))
            return enemy.getPosition();
        //0: Đứng yên, 1: Đi lên, 2: Đi xuống, 3: Đi qua phải, 4: Đi Trái
        int direction= 0;
        int sidedirection = 0;
        Int2 temp = new Int2(0,0);
        temp.x = enemy.getPosition().x;
        temp.y = enemy.getPosition().y;
        if (Math.abs(distance.x) >= Math.abs(distance.y) && (distance.x > 0 || distance.x < 0))
        {
            if (distance.x > 0)
                direction = 4;
            else
                direction = 3;
            
            if (distance.y > 0)
                sidedirection = 1;
            else
                sidedirection = 2;
        }
        else if (Math.abs(distance.x) < Math.abs(distance.y) && (distance.y > 0 || distance.y < 0))
        {
            if (distance.y > 0)
                direction = 1;
            else
                direction = 2;
            
            if (distance.x > 0)
                sidedirection = 4;
            else
                sidedirection = 3;
        }
        
        //Tìm đường lần 1, nếu không thể đi đường đó đến tìm đường lần 2
        switch(direction)
        {
            case 1:
                temp.x = enemy.getPosition().x;
                temp.y = enemy.getPosition().y;
                temp.y -= enemy.getMovement().speed;
                if (enemy.getMovement().canMove(temp))
                    return temp;
                else
                    break;
            case 2:
                temp.x = enemy.getPosition().x;
                temp.y = enemy.getPosition().y;
                temp.y += enemy.getMovement().speed;
                if (enemy.getMovement().canMove(temp))
                    return temp;
                else
                    break;
            case 3:
                temp.x = enemy.getPosition().x;
                temp.y = enemy.getPosition().y;
                temp.x += enemy.getMovement().speed;
                if (enemy.getMovement().canMove(temp))
                    return temp;
                else
                    break;
            case 4:
                temp.x = enemy.getPosition().x;
                temp.y = enemy.getPosition().y;
                temp.x -= enemy.getMovement().speed;
                if (enemy.getMovement().canMove(temp))
                    return temp;
                else
                    break;
                
        }
        
        //Tìm đường lần 2, nếu không thể đi đường đó đến tìm đường lần 3
        switch(sidedirection)
            {
            case 1:
                temp.x = enemy.getPosition().x;
                temp.y = enemy.getPosition().y;
                temp.y -= enemy.getMovement().speed;
                if (enemy.getMovement().canMove(temp))
                    return temp;
                else
                    break;
            case 2:
                temp.x = enemy.getPosition().x;
                temp.y = enemy.getPosition().y;
                temp.y += enemy.getMovement().speed;
                if (enemy.getMovement().canMove(temp))
                    return temp;
                else
                    break;
            case 3:
                temp.x = enemy.getPosition().x;
                temp.y = enemy.getPosition().y;
                temp.x += enemy.getMovement().speed;
                if (enemy.getMovement().canMove(temp))
                    return temp;
                else
                    break;
            case 4:
                temp.x = enemy.getPosition().x;
                temp.y = enemy.getPosition().y;
                temp.x -= enemy.getMovement().speed;
                if (enemy.getMovement().canMove(temp))
                    return temp;
                else
                    break;
            }
        
        //Tìm đường lần 3 nếu không thể đi đường đó đến tìm đường lần 4 (Tìm đường lần 3 là đi ngược hướng tìm đường lần 1)
        switch(direction)
        {
            case 1:
                temp.x = enemy.getPosition().x;
                temp.y = enemy.getPosition().y;
                temp.y += enemy.getMovement().speed;
                if (enemy.getMovement().canMove(temp))
                    return temp;
                else
                    break;
            case 2:
                temp.x = enemy.getPosition().x;
                temp.y = enemy.getPosition().y;
                temp.y -= enemy.getMovement().speed;
                if (enemy.getMovement().canMove(temp))
                    return temp;
                else
                    break;
            case 3:
                temp.x = enemy.getPosition().x;
                temp.y = enemy.getPosition().y;
                temp.x -= enemy.getMovement().speed;
                if (enemy.getMovement().canMove(temp))
                    return temp;
                else
                    break;
            case 4:
                temp.x = enemy.getPosition().x;
                temp.y = enemy.getPosition().y;
                temp.x += enemy.getMovement().speed;
                if (enemy.getMovement().canMove(temp))
                    return temp;
                else
                    break; 
        }
        
        //Tìm đường lần 4 nếu không thể đi đường đó thì đứng yên tại chỗ (Tìm đường lần 4  là đi ngược hướng tìm đường lần 2)
        switch(sidedirection)
        {
        case 1:
            temp.x = enemy.getPosition().x;
            temp.y = enemy.getPosition().y;
            temp.y -= enemy.getMovement().speed;
            if (enemy.getMovement().canMove(temp))
                return temp;
            else
                break;
        case 2:
            temp.x = enemy.getPosition().x;
            temp.y = enemy.getPosition().y;
            temp.y += enemy.getMovement().speed;
            if (enemy.getMovement().canMove(temp))
                return temp;
            else
                break;
            case 3:
            temp.x = enemy.getPosition().x;
            temp.y = enemy.getPosition().y;
            temp.x += enemy.getMovement().speed;
            if (enemy.getMovement().canMove(temp))
                return temp;
            else
                break;
        case 4:
            temp.x = enemy.getPosition().x;
            temp.y = enemy.getPosition().y;
            temp.x -= enemy.getMovement().speed;
            if (enemy.getMovement().canMove(temp))
                return temp;
            else
                break;
        }
        temp.x = enemy.getPosition().x;
        temp.y = enemy.getPosition().y;
        return temp;
    }
    
}
