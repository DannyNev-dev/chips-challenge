package nz.ac.vuw.ecs.swen225.gp20.application;

import java.io.File;

/**
 *
 * @author camilalis 300504575
 * From Master
 */
class JsonFileFilter extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept(File file) {
            return file.isDirectory() || file.getAbsolutePath().endsWith(".json");
        }
        @Override
        public String getDescription() {
            return "Recorded Game (*.json)";
        }
}
