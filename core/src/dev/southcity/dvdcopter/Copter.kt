package dev.southcity.dvdcopter

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable
import kotlin.random.Random

class Copter : Disposable {
    companion object {
        const val SPEED: Float = 100f
    }

    private var texture: Texture = Texture("plane.png")

    private var position: Vector2 = Vector2(
        DvdVideoCopterGame.SCREEN_WIDTH / 2f - texture.width / 2f,
        DvdVideoCopterGame.SCREEN_HEIGHT / 2f - texture.height / 2f
    )

    private var velocity: Vector2 = Vector2(
        Random.nextFloat() - 0.5f,
        Random.nextFloat() - 0.5f,
    ).setLength(SPEED)

    fun update(delta: Float) {
        position.x += velocity.x * delta
        position.y += velocity.y * delta

        if (position.x <= 0 || position.x + texture.width >= DvdVideoCopterGame.SCREEN_WIDTH) {
            position.x = position.x.coerceIn(0f, DvdVideoCopterGame.SCREEN_WIDTH) // coercing ensures that the plane doesn't escape the screen for a single frame when at high speeds
            velocity.x *= -1
        }

        if (position.y <= 0 || position.y + texture.height >= DvdVideoCopterGame.SCREEN_HEIGHT) {
            position.y = position.y.coerceIn(0f, DvdVideoCopterGame.SCREEN_HEIGHT)
            velocity.y *= -1
        }
    }

    fun draw(batch: SpriteBatch) {
        // this call can be simplified by using libgdx's "Sprite" class, but that comes with extra overhead
        batch.draw(
            texture,
            position.x, position.y, // coordinates
            texture.width / 2f, texture.height / 2f, // origin for scale and rotation
            texture.width.toFloat(), texture.height.toFloat(), // dimensions
            1f, 1f, // scaling
            velocity.angleDeg() - 90, // rotation
            0, 0, // source rect bottom left
            texture.width, texture.height, // source rect dimensions
            false, false // flips
        )
    }

    override fun dispose() {
        texture.dispose()
    }
}