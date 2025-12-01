package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        // TODO: Call reset()/
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }
        val GRAVITY_SCALE = 15f

        val gravityX = -xAcc * GRAVITY_SCALE
        val gravityY = yAcc * GRAVITY_SCALE

        println("Ball - xAcc: $xAcc, gravityX: $gravityX, deltaX will be calculated")
        // Equation 1: v1 = v0 + 1/2(a1 + a0)(t1 - t0)
        val newVelocityX = velocityX + 0.5f * (accX + gravityX) * dT
        val newVelocityY = velocityY + 0.5f * (accY + gravityY) * dT

        // Equation 2: l = v0·(t1-t0) + 1/6·(t1-t0)²·(3a0 + a1)
        val deltaX = velocityX * dT + (1f / 6f) * dT * dT * (3 * accX + gravityX)
        val deltaY = velocityY * dT + (1f / 6f) * dT * dT * (3 * accY + gravityY)
        posX += deltaX
        posY += deltaY

        println("Ball - posX after: $posX")

        velocityX = newVelocityX
        velocityY = newVelocityY

        accX = gravityX
        accY = gravityY
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)
        val radius = ballSize / 2f

        // Left boundary
        if (posX < 0) {
            posX = 0f
            velocityX = -velocityX * 0.2f
            accX = 0f
        }

        // Right boundary - account for ball width
        if (posX + ballSize > backgroundWidth) {
            posX = backgroundWidth - ballSize
            velocityX = -velocityX * 0.2f
            accX = 0f
        }

        // Top boundary
        if (posY < 0) {
            posY = 0f
            velocityY = -velocityY * 0.2f
            accY = 0f
        }

        // Bottom boundary - account for ball height
        if (posY + ballSize > backgroundHeight) {
            posY = backgroundHeight - ballSize
            velocityY = -velocityY * 0.2f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = backgroundWidth / 2f
        posY = backgroundHeight / 2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}