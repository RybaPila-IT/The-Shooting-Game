package The.Duck.Game;

import FXMLControlers.BasicController;
import javafx.scene.layout.Region;

import java.util.List;

public class Bullet extends BoardObject {

    private static final String BULLET_STYLE = "bullet";
    private static final double BULLET_WIDTH = 16;
    private static final double BULLET_HEIGHT = 16;
    private static final double BULLET_SPEED = 40;
    private static final int ON_OBSTACLE = 10;
    private static final int ON_PLAYER = 3;

    private final BasicController controller;
    private final boolean isBulletFacingRight;

    private boolean onObstacle;
    private int onObstacleWait;

    public Bullet(Rectangle weapon, boolean isWeaponFacingRight) {

        super(new Rectangle(isWeaponFacingRight ? weapon.getSecondX() : weapon.getLayoutX(),
                weapon.getSecondY(), BULLET_WIDTH, BULLET_HEIGHT));

        isBulletFacingRight = isWeaponFacingRight;
        onObstacle = false;
        onObstacleWait = ON_OBSTACLE;
        controller = new BasicController(BoardConstants.getController().createNewRegion(region, BULLET_STYLE));
    }

    private void changeBulletPosition() {
        region.addHorizontally(isBulletFacingRight ? BULLET_SPEED : -BULLET_SPEED);
    }

    public void setOnObstacle(boolean onObstacle) {
        this.onObstacle = onObstacle;
    }

    public boolean isBulletFacingRight() {
        return isBulletFacingRight;
    }

    private void handleObstacleCollision() {

        List<BoardObject> objects = BoardElements.getInstance().collidedWith(region);

        for (BoardObject object : objects)
            if (object instanceof BulletCollisionSensitive) {
                BulletCollisionSensitive collided = (BulletCollisionSensitive) object;
                collided.onBulletCollision(this);
            }

    }

    private void informControllerAboutPosition() {
        controller.setLayoutY(region.getLayoutY());
        controller.setLayoutX(region.getLayoutX());
    }

    private void moveBulletOutOfBoard() {
        region.setX(BoardConstants.getBoardWidth() + 400);
    }

    public void moveBullet() {

        if (!onObstacle) {
            changeBulletPosition();
            handleObstacleCollision();
        } else if (onObstacleWait-- <= 0)
            moveBulletOutOfBoard();

        informControllerAboutPosition();
    }

    public boolean outsideBoard() {
        return region.getLayoutX() > BoardConstants.getBoardWidth() ||
                region.getSecondX() < 0;
    }

    public void removeBulletFromScene() {
        controller.remove();
    }

    @Override
    public void onTic() {

        moveBullet();

        if (outsideBoard())
            removeBulletFromScene();

    }

    @Override
    public void onPlayerCollision(Player player) {

        if (!onObstacle) {

            if (!player.isDead())
                player.decreaseHealth();

            region.setX(player.getRegion().getLayoutX() + 15);
            onObstacle = true;
            onObstacleWait = ON_PLAYER;
            Blood blood = new Blood(region.getLayoutX(), region.getLayoutY());
            BoardElements.getInstance().addBoardObject(blood);
        }

    }

    @Override
    public boolean isValid() {
        return !outsideBoard();
    }

    public boolean equals(Object obj) {

        if (obj instanceof Bullet) {
            Bullet o = (Bullet) obj;
            return region.equals(o.region) &&
                    onObstacle == o.onObstacle &&
                    isBulletFacingRight == o.isBulletFacingRight &&
                    onObstacleWait == o.onObstacleWait;
        }

        return false;
    }
}
