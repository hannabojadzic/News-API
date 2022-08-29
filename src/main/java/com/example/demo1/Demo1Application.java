package com.example.demo1;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Demo1Application implements CommandLineRunner {
	@Autowired
	NewsRepository newsRepository;


	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		newsRepository.save(
				News
						.builder()
						.id(1L)
						.description("Peiter “Mudge” Zatko, the former Twitter security chief whose explosive disclosures alleged serious security flaws and misleading practices within the company, has received a subpoena to appear for a deposition in the ongoing lawsuit between Twitter and Elon Musk.\nThe request puts Twitter’s whistleblower at the center of Musk’s ongoing legal fight with Twitter, which he agreed to purchase in April. Musk has sought to void the deal, citing many of the same issues detailed in Zatko’s disclosures.\nMusk’s legal team took action shortly after Zatko’s claims became public, telling reporters they were in the process of filing a subpoena as early as August 23rd. However, the documents were only officially filed on Monday, giving the request legal force and making its details public for the first time.")
						.headline("The Twitter whistleblower just got a subpoena from Elon Musk")
						.imageUrl("https://cdn.vox-cdn.com/thumbor/EnH0kmAiYtUM0eNpa9GcKwRTeIY=/0x0:2040x1360/920x613/filters:focal(857x517:1183x843):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/71297649/VRG_Illo_STK022_K_Radtke_Musk_Bolts.0.jpg")
						.build()
		);
		newsRepository.save(
				News
						.builder()
						.id(2L)
						.description("AMD is expected to launch four CPUs in its Ryzen 7000 series (the “X” line) this evening, and a few hours ahead of the announcement, the pricing has already leaked. These prices, as Wccftech correctly notes, remain unchanged from the prices of the Zen 3 Ryzen 5000 lineup, which also ranged from $299 to $799 at release. The 7000 series is the direct successor to the 5000 desktop series — the 6000 series (currently trickling out in certain laptop models) skipped the desktop chips.\n" +
								"\n" +
								"Much of what we know about these upcoming Ryzen 7000 CPUs comes from AMD’s Computex keynote earlier this year. They’re set to be the first desktop CPUs based on a 5nm process. They’re also the first AMD chips to boast boost clocks over 5GHz — the CPU matched the 5.5GHz output of Intel’s flagship Core i9-12900KS in a run of Ghostwire: Tokyo onstage.")
						.headline("AMD won’t change launch pricing for Ryzen 7000 series, according to new leak")
						.imageUrl("https://cdn.vox-cdn.com/thumbor/EnH0kmAiYtUM0eNpa9GcKwRTeIY=/0x0:2040x1360/920x613/filters:focal(857x517:1183x843):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/71297649/VRG_Illo_STK022_K_Radtke_Musk_Bolts.0.jpg")
						.build()
		);

	}

}

@RestController
class Hello {
	@Autowired
	NewsRepository newsRepository;

	@RequestMapping("/")
	String index() {
		return "News application 1";
	}

	@RequestMapping(value = "/news", method = RequestMethod.GET,  produces = MediaType.APPLICATION_XML_VALUE)
	List<News> getSingleInventoryEndpoint() throws InterruptedException {
		//XmlMapper xmlMapper = new XmlMapper();
		//String personXml = xmlMapper.writeValueAsString(person);
		return newsRepository.findAll();
	}
}

@Data
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@XmlRootElement
class News {
	@Id
	Long id;
	String headline;
	String imageUrl;
	@Column(length = 5000)
	String description;

	public News() {

	}
}


