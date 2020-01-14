package org.transformers.operatorfabric;

import org.lfenergy.operatorfabric.cards.model.*;
import org.opensmartgridplatform.adapter.kafka.MeterReading;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.transformers.opensmartgridplatform.IntervalReading;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class CardPublisher {

    public Card createMeterReadingCard(MeterReading meterReading) {

        Card card = new Card();

        String process = "current_meter_reading";

        card.setProcess(process);
        card.setProcessId(process+meterReading.getName());
        card.setPublisher("TRANSFORMERS");
        card.setPublisherVersion("1");
        //TODO Set id ?

        //TODO Get dates from the card
        card.setLttd(Instant.now().toEpochMilli());
        card.setStartDate(Instant.now().toEpochMilli());
        card.setEndDate(Instant.now().toEpochMilli() + 2*60*60*1000);

        card.setSeverity(SeverityEnum.NOTIFICATION);

        Recipient adminUser = new Recipient();
        adminUser.setType(RecipientEnum.USER);
        adminUser.setIdentity("admin");
        card.setRecipient(adminUser);

        I18n i18nTitle = new I18n();
        i18nTitle.setKey(process+".title");
        HashMap<String,String> i18nTitleParams = new HashMap<>();
        i18nTitleParams.put("value",meterReading.getUsagePoint().getMRid());
        i18nTitle.setParameters(i18nTitleParams);
        card.setTitle(i18nTitle);

        I18n i18nSummary = new I18n();
        i18nSummary.setKey(process+".summary");
        HashMap<String,String> i18nSummaryParams = new HashMap<>();
        i18nSummaryParams.put("value",meterReading.getIntervalBlocks().get(0).getReadingType().getMeasuringKind()); //TODO Add interval as well
        i18nSummary.setParameters(i18nSummaryParams);
        card.setSummary(i18nSummary);

        card.setState("firstState");

        //TODO Fix this to avoid burning in hell
        IntervalReading intervalReading = new IntervalReading(meterReading.getIntervalBlocks().get(0).getIntervalReadings().get(0).getValue(),meterReading.getIntervalBlocks().get(0).getIntervalReadings().get(0).getTimeStamp());
        System.out.println("AGU timestamp "+meterReading.getIntervalBlocks().get(0).getIntervalReadings().get(0).getTimeStamp().toString());
        card.setData(intervalReading);

        return card;
    }

    public CardCreationReport publishCard(Card card) {

        //TODO Put it in application.yml?
        String uri = "http://localhost:2102/cards";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        /*
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");*/

        HttpEntity<Card> request = new HttpEntity<>(card, headers);

        RestTemplate restTemplate = new RestTemplate();
        CardCreationReport result = restTemplate.postForObject(uri, request, CardCreationReport.class);

        return result;

    }

}
