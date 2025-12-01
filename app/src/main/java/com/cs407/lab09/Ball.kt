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
        val gravityX = -xAcc
        val gravityY = -yAcc

        // Equation 1: v1 = v0 + 1/2(a1 + a0)(t1 - t0)
        val newVelocityX = velocityX + 0.5f * (accX + gravityX) * dT
        val newVelocityY = velocityY + 0.5f * (accY + gravityY) * dT

        // Equation 2: l = v0·(t1-t0) + 1/6·(t1-t0)²·(3a0 + a1)
        val deltaX = velocityX * dT + (1f / 6f) * dT * dT * (3 * accX + gravityX)
        val deltaY = velocityY * dT + (1f / 6f) * dT * dT * (3 * accY + gravityY)

        posX += deltaX
        posY += deltaY

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

        //left boundary
        if (posX - radius < 0) {
            posX = radius
            velocityX = -velocityX * 0.8f
            accX = 0f
        }

        //right boundary
        if (posX + radius > backgroundWidth) {
            posX = backgroundWidth - radius
            velocityX = -velocityX * 0.8f
            accX = 0f
        }

        //top boundary
        if (posY - radius < 0) {
            posY = radius
            velocityY = -velocityY * 0.8f
            accY = 0f
        }

        //bottom boundary
        if (posY + radius > backgroundHeight) {
            posY = backgroundHeight - radius
            velocityY = -velocityY * 0.8f
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