package com.cqhg.ensure.contoller;

import com.cqhg.ensure.entity.*;
import com.cqhg.ensure.service.*;
import com.cqhg.ensure.util.JsonResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 测试数据是否可以正常保存
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private CarDataService carDataService;
    @Autowired
    private CargoTransService cargoTransService;
    @Autowired
    private LowinfoDataService lowinfoDataService;
    @Autowired
    private MarryDataService marryDataService;
    @Autowired
    private HuJiService huJiService;
    @Autowired
    private DivorceService divorceService;
    @Autowired
    private TaxiTransService taxiTransService;

    @Autowired
    private TownWorkerService townWorkerService;
    @Autowired
    private TrafficTransitService trafficTransitService;


    @RequestMapping("addCarData")
    public Map addCarData(String cardNo){
        ArrayList<CarData> dataList = new ArrayList<>();

        CarData data = new CarData();
        data.setCardNo(cardNo);
        CarData data2 = new CarData();
        data2.setCardNo(cardNo+"2");

        dataList.add(data);
        dataList.add(data2);

        String peopleId = "peopleId";
        String commissionId = "commissionId";
        carDataService.addList(dataList,peopleId,commissionId);
        return JsonResultUtils.success("成功!");
    }

    @RequestMapping("addCargoTrans")
    public Map addCargoTrans(String xm){
        ArrayList<CargoTransportation> dataList = new ArrayList<>();

        CargoTransportation data = new CargoTransportation();
        data.setXM(xm);
        CargoTransportation data2 = new CargoTransportation();
        data2.setXM(xm+"2");

        dataList.add(data);
        dataList.add(data2);

        String peopleId = "peopleId";
        String commissionId = "commissionId";
        cargoTransService.addList(dataList,peopleId,commissionId);
        return JsonResultUtils.success("成功!");
    }

    @RequestMapping("addHuJi")
    public Map addHuJi(String message,String code){
        ArrayList<HuJiData> dataList = new ArrayList<>();
        HuJiData data = new HuJiData(message,code);
        HuJiData data2 = new HuJiData(message+"addHuJi",code);
        dataList.add(data);
        dataList.add(data2);

        String peopleId = "peopleId";
        String commissionId = "commissionId";
        huJiService.addList(dataList,peopleId,commissionId);
        return JsonResultUtils.success("成功!");
    }

    @RequestMapping("addDivorce")
    public Map addDivorce(String cardNo,String code){
        ArrayList<DivorceData> dataList = new ArrayList<>();
        DivorceData data = new DivorceData();
        data.setCardNo(cardNo);
        DivorceData data2 = new DivorceData();
        data2.setCardNo(cardNo+"addDivorce");
        dataList.add(data);
        dataList.add(data2);

        String peopleId = "peopleId";
        String commissionId = "commissionId";
        divorceService.addList(dataList,peopleId,commissionId);
        return JsonResultUtils.success("成功!");
    }


    @RequestMapping("addLowinfoData")
    public Map addLowinfoData(String income, String address,String PersonName){
        ArrayList<LowinfoData> lowinfoDataList = new ArrayList<>();
        LowinfoData lowinfoData = new LowinfoData();
        lowinfoData.setAddress(address);
        lowinfoData.setIncome(income);
        lowinfoData.setPersonName(PersonName);

        LowinfoData lowinfoData2 = new LowinfoData();
        lowinfoData2.setAddress(address+2);
        lowinfoData2.setIncome(income+2);
        lowinfoData2.setPersonName(PersonName+2);
        lowinfoDataList.add(lowinfoData);
        lowinfoDataList.add(lowinfoData2);

        String peopleId = "peopleId";
        String commissionId = "commissionId";
        lowinfoDataService.addLowinfoDataList(lowinfoDataList,peopleId,commissionId);
        return JsonResultUtils.success("成功!");
    }


    @RequestMapping("addMarryData")
    public Map addMarryData(String xm){
        List<MarryData> dataList = new ArrayList<>();

        MarryData data = new MarryData();
        data.setPersonName(xm);
        MarryData data2 = new MarryData();
        data2.setPersonName(xm+"addMarryData");

        dataList.add(data);
        dataList.add(data2);

        String peopleId = "peopleId";
        String commissionId = "commissionId";
        marryDataService.addList(dataList,peopleId,commissionId);
        return JsonResultUtils.success("成功!");
    }

    @RequestMapping("addTownWorker")
    public Map addTownWorker(String xm){
        ArrayList<TownWorkerInsurance> dataList = new ArrayList<>();

        TownWorkerInsurance data = new TownWorkerInsurance();
        data.setAac002(xm);
        TownWorkerInsurance data2 = new TownWorkerInsurance();
        data2.setAac002(xm+"2");

        dataList.add(data);
        dataList.add(data2);

        String peopleId = "peopleId";
        String commissionId = "commissionId";
        townWorkerService.addList(dataList,peopleId,commissionId);
        return JsonResultUtils.success("成功!");
    }

    @RequestMapping("addTaxiTrans")
    public Map addTaxiTrans(String xm){
        ArrayList<TaxiTransportation> dataList = new ArrayList<>();

        TaxiTransportation data = new TaxiTransportation();
        data.setXM(xm);
        TaxiTransportation data2 = new TaxiTransportation();
        data2.setXM(xm+"2");

        dataList.add(data);
        dataList.add(data2);

        String peopleId = "peopleId";
        String commissionId = "commissionId";
        taxiTransService.addList(dataList,peopleId,commissionId);
        return JsonResultUtils.success("成功!");
    }

    @RequestMapping("addTrafficTransit")
    public Map addTrafficTransit(String xm){
        ArrayList<TrafficTransit> dataList = new ArrayList<>();

        TrafficTransit data = new TrafficTransit();
        data.setXM(xm);
        TrafficTransit data2 = new TrafficTransit();
        data2.setXM(xm+"2");

        dataList.add(data);
        dataList.add(data2);

        String peopleId = "peopleId";
        String commissionId = "commissionId";
        trafficTransitService.addList(dataList,peopleId,commissionId);
        return JsonResultUtils.success("成功!");
    }
}
