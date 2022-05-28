package com.schedule.runnable;

import org.springframework.stereotype.Service;

@Service
public class HoaDonRunable implements Runnable {

	@Override
	public void run() {
		
	}

//	private static final Logger LOG = LoggerFactory.getLogger(HoaDonRunable.class);
//
//	@Autowired
//	private KafkaTemplate<String, Object> kafkaTemplate;
//
//	@Autowired
//	private TVANTDiepMQService tvanTDiepMQService;
//
//	@Autowired
//	private TVANGuiHoaDonService tvanGuiHoaDonService;
//
//	@Autowired
//	private TVANLogsGiaoDichService tvanLogsGiaoDichService;
//
//	@Autowired
//	private TVANGuiHoaDonBase64Service tvanGuiHoaDonBase64Service;
//
//	@Autowired
//	private TVANGuiHoaDonBase64LuuTruService tvanGuiHoaDonBase64LuuTruService;
//
//	private static final Set<String> poolIds = Collections.synchronizedSet(new HashSet<>());
//
//	private final String MANOIGUI = "V0309478306", MANOINHAN = "TCT";
//
//	private String threadname;
//	private List<TVANGuiHoaDon> data;
//	
//	int countSend = 0;
//	
//	public HoaDonRunable() {
//		
//	}
//	
//	public HoaDonRunable(String threadname, List<TVANGuiHoaDon> data) {
//		this.threadname = threadname;
//		this.data = new ArrayList<>(data);
//	}
//
//	@Override
//	public void run() {
//		send();
//	}
//
//	@SuppressWarnings("unchecked")
//	private void send() {
//		LOG.info("Thread {} start", threadname);
//		kafkaTemplate = SpringUtils.CTX.getBean(KafkaTemplate.class);
//		tvanTDiepMQService = SpringUtils.CTX.getBean(TVANTDiepMQService.class);
//		tvanGuiHoaDonService = SpringUtils.CTX.getBean(TVANGuiHoaDonService.class);
//		tvanLogsGiaoDichService = SpringUtils.CTX.getBean(TVANLogsGiaoDichService.class);
//		tvanGuiHoaDonBase64Service = SpringUtils.CTX.getBean(TVANGuiHoaDonBase64Service.class);
//		tvanGuiHoaDonBase64LuuTruService = SpringUtils.CTX.getBean(TVANGuiHoaDonBase64LuuTruService.class);
//
//		LOG.info("Thread {}, size data = {}", threadname, data.size());
//		if (data.size() == 0) {
//			PutHoaDon.getNThread();
//		}
//		for (TVANGuiHoaDon hoadon : data) {
//			String guid = hoadon.getGuid().trim();
//			if (poolIds.add(guid)) {
//				TVANGuiHoaDonBase64 hoadonBase64 = tvanGuiHoaDonBase64Service.findTVANGuiHoaDonByGuid(guid);
//				if (hoadonBase64 != null && StringUtils.isNotEmpty(hoadonBase64.getNoidungBase64())) {
//					try {
//						ListenableFuture<SendResult<String, Object>> future = //
//								kafkaTemplate.send(ConfigReader.KAFKA_TOPIC_PRODUCER, Base64Utils.decodeToString(hoadonBase64.getNoidungBase64()));
//						TVANLogsGiaoDich log = new TVANLogsGiaoDich();
//						String maloaithongdiep = hoadon.getMaloaiTdiep(); // 102, 103, ...
//						String mathongdiep = hoadon.getMatdiep(); // Vxxx
//						log.setGuid(UUID.randomUUID().toString());
//						log.setCode("PUT");
//						log.setMltdiep(maloaithongdiep);
//						log.setMtdiep(mathongdiep);
//						log.setMasothue(hoadon.getMasothue().replaceAll("-", ""));
//						log.setTkhoanapi(hoadon.getIdUser());
//						log.setManoigui(MANOIGUI);
//						log.setManoinhan(MANOINHAN);
//						if (maloaithongdiep.contains("999")) {
//							log.setMtdieptchieu(hoadon.getMathdiepThamchieu());
//						} else {
//							log.setMtdieptchieu(mathongdiep);
//						}
//						log.setMatdieptcgp(hoadon.getMatdieptcgp());
//						log.setNam(Integer.parseInt(tvanTDiepMQService.getCurrentYear()));
//						log.setThang(Integer.parseInt(tvanTDiepMQService.getCurrentMonth()));
//
//						log.setDateGd(tvanTDiepMQService.getMySqlDateTimeCur());
//						log.setCreateTime(tvanTDiepMQService.getMySqlDatetimeMilliseconds());
//						future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
//							@Override
//							public void onSuccess(SendResult<String, Object> result) {
//								LOG.info("Put success message to partition: {}", result.getRecordMetadata().partition());
//								String mtdiep = hoadon.getMatdiep();
//								if (hoadon.getMaloaiTdiep().contains("999")) {
//									mtdiep = hoadon.getMathdiepThamchieu();
//								}
//								log.setMota("PUT_OK");
//
//								tvanGuiHoaDonService.updateTinhTrangGui(guid, 1);
//								poolIds.remove(guid);
//								if (hoadonBase64 != null) {
//									if (!hoadon.getMaloaiTdiep().contains("999")) {
//										TVANGuiHoaDonBase64LuuTru objLuuTru = new TVANGuiHoaDonBase64LuuTru();
//										objLuuTru.setGuid(hoadonBase64.getGuid());
//										objLuuTru.setCreateTime(hoadonBase64.getCreateTime());
//										objLuuTru.setMaTDiep(hoadonBase64.getMaTDiep());
//										objLuuTru.setNam(hoadonBase64.getNam());
//										objLuuTru.setThang(hoadonBase64.getThang());
//										objLuuTru.setNoidungBase64(hoadonBase64.getNoidungBase64());
//										tvanGuiHoaDonBase64LuuTruService.saveBase(objLuuTru);
//									}
//								}
//								
//								tvanGuiHoaDonBase64Service.deleteBase64ByGuid(guid);
//								tvanLogsGiaoDichService.saveLog(log);
//								
//								LOG.info("[PutHoaDon] - Id={" + guid + "} update_tvan_gui_hoadon, " //
//										+ "insert_tvan_hoadon_base64_luutru, remove_tvan_hoadon_base64, save_tvan_logs_giaodich...");
//
//								LOG.info("Pool size: " + poolIds.size());
//								LOG.info("[PutHoaDon] - Thread = {}, Id = {} put success: {}", threadname, mtdiep, tvanTDiepMQService.getMySqlDateTimeCur());
//								increment();
//								LOG.info("Thread = {}, Size data = {}, coutSendDone = {}", threadname, data.size(), countSend);
//							}
//
//							@Override
//							public void onFailure(Throwable e) {
//								poolIds.remove(guid);
//								increment();
//								LOG.info("[PutHoaDon] - Id={" + guid + "} put fail: " + e.toString());
//							}
//						});
//					} catch (Exception e) {
//						increment();
//						poolIds.remove(guid);
//						LOG.error(e.getMessage());
//					}
//				} else {
//					String mtdiep_err = hoadon.getMatdiep();
//					if (hoadon.getMaloaiTdiep().contains("999")) {
//						mtdiep_err = hoadon.getMathdiepThamchieu();
//					}
//					poolIds.remove(guid);
//					increment();
//					LOG.info("[PutHoaDon] - Id={" + mtdiep_err + "} no message in tvan_hoadon_base64 to send...");
//				}
//			}
//		}
//	}
//	
//	void increment() {
//		countSend++;
//		if (countSend == data.size()) {
//			// PutHoaDon.nThread += 1;
//			PutHoaDon.setNThread();
//			LOG.info("Thread {} end, nThread = {}", threadname, PutHoaDon.getNThread());
//		}
//	}

}
