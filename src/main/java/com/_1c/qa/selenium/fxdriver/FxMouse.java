/*
 * Copyright 2018 1C-Soft LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com._1c.qa.selenium.fxdriver;

import com._1c.qa.selenium.fxdriver.robot.IMouseFxRobot;
import javafx.scene.Node;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.MouseButton;

public class FxMouse implements Mouse {
    private final IMouseFxRobot<?> robot;

    public FxMouse(IMouseFxRobot<?> robot) {
        this.robot = robot;
    }

    @Override
    public void click(Coordinates where) {
        if (where != null) {
            NodeUtils.scrollIntoView((Node) where.getAuxiliary());
            this.robot.click(where.onScreen().x, where.onScreen().y);
        } else {
            this.robot.click();
        }
    }

    @Override
    public void doubleClick(Coordinates where) {
        if (where != null) {
            NodeUtils.scrollIntoView((Node) where.getAuxiliary());
            this.robot.doubleClick(where.onScreen().x, where.onScreen().y);
        } else {
            this.robot.doubleClick(MouseButton.LEFT);
        }
    }

    @Override
    public void mouseDown(Coordinates where) {
        if (where != null) {
            this.robot.move(where.onScreen().x, where.onScreen().y);
        }
        this.robot.mouseDown(PointerInput.MouseButton.LEFT);
    }

    @Override
    public void mouseUp(Coordinates where) {
        if (where != null) {
            this.robot.move(where.onScreen().x, where.onScreen().y);
        }
        this.robot.mouseUp(PointerInput.MouseButton.LEFT);
    }

    @Override
    public void mouseMove(Coordinates where) {
        if (where != null) {
            NodeUtils.scrollIntoView((Node) where.getAuxiliary());
            this.robot.move(where.onScreen().x, where.onScreen().y);
        }
    }

    @Override
    public void mouseMove(Coordinates where, long xOffset, long yOffset) {
        if (where != null) {
            NodeUtils.scrollIntoView((Node) where.getAuxiliary());
            this.robot.move((int) (where.onScreen().x + xOffset), (int) (where.onScreen().y + yOffset));
        } else {
            this.robot.move((int) xOffset, (int) yOffset);
        }
    }

    @Override
    public void contextClick(Coordinates where) {
        if (where == null)
            this.robot.click(PointerInput.MouseButton.RIGHT);
        else {
            NodeUtils.scrollIntoView((Node) where.getAuxiliary());
            this.robot.click(where.onScreen().x, where.onScreen().y, PointerInput.MouseButton.RIGHT);
        }
    }
}
