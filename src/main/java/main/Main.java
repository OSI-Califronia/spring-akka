package main;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by OSI on 25.03.2016.
 */
@Slf4j
public class Main {

	private void testerino() {
		log.info("Starting the app");
	}
	
    public final static void main(String[] args) {
        Main main = new Main();
		main.testerino();
    }
}
