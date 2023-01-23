package dev.southcity.dvdcopter

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils

class DvdVideoCopterGame : ApplicationAdapter() {
    companion object {
        const val SCREEN_WIDTH = 360f
        const val SCREEN_HEIGHT = 240f
    }

    private lateinit var batch: SpriteBatch
    private lateinit var camera: OrthographicCamera
    private lateinit var copter: Copter

    override fun create() {
        batch = SpriteBatch()
        camera = OrthographicCamera()
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT)
        copter = Copter()
    }

    override fun render() {
        val deltaTime = Gdx.graphics.deltaTime

        copter.update(deltaTime)

        ScreenUtils.clear(0f, 0f, 0f, 1f)
        batch.projectionMatrix = camera.combined
        batch.begin()
        copter.draw(batch)
        batch.end()
    }

    override fun dispose() {
        batch.dispose()
        copter.dispose()
    }
}