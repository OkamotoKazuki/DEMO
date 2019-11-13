package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//serviceパッケージもDIできるようにパスを指定する
@SpringBootApplication(scanBasePackages={"controller" ,"service"})
public class DemoApplication {

	// mainメソッドを持つクラスがプロジェクト内に無いと起動できない
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
