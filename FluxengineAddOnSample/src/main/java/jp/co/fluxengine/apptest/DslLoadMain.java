package jp.co.fluxengine.apptest;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jersey.repackaged.com.google.common.collect.Maps;
import jp.co.fluxengine.stateengine.constant.Constant.COMMON_DSL;
import jp.co.fluxengine.stateengine.dslreader.DslProvider;
import jp.co.fluxengine.stateengine.dslreader.DslRegister;
import jp.co.fluxengine.stateengine.model.Definition.EventDatomDef;
import jp.co.fluxengine.stateengine.model.datom.Event;
import jp.co.fluxengine.stateengine.starter.Fluxengine;
import jp.co.fluxengine.stateengine.util.typeconverter.TypeConverter;

import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;

public class DslLoadMain {

	private static String sinarioFileName;

	private static String dslFilePath;

	public static void main(String[] args) {

//		LoggerContext logContext = (LoggerContext) LogManager.getContext(false);
//		File conFile = new File(System.getenv().get("CONF") + File.separator + "log4j.xml");
//		logContext.setConfigLocation(conFile.toURI());
//		logContext.reconfigure();

		dslFilePath = args[0];

		dslFilePath = "C:/pleiades4.4/workspace/FluxengineAddOnSample/src/main/dsl/packet/";
		File file = new File(dslFilePath);

//		if (args.length >= 2) {
//			sinarioFileName = file.getPath() + File.separator
//					+ StringUtils.defaultIfEmpty(args[1], "input-event.txt");
//		} else {
			sinarioFileName = file.getPath() + File.separator
					+ "input-event.txt";
//		}

		// new Thread() {
		// public void run() {
		// JUnitCore.runClasses(new Class[] { dslLoadMain.class });
		// }
		// }.start();
		DslLoadMain main = new DslLoadMain();
		try {
			main.execteSenalio();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execteSenalio() throws Exception {

		registerDsl();

		Yaml yaml = new Yaml();

		Object object = yaml.load(new FileReader(sinarioFileName));

		Map<Date, Object> map = (Map<Date, Object>) object;
		if (map != null) {
			for (Entry<Date, Object> entry : map.entrySet()) {

				List<Event> enents = Lists.newArrayList();
				Map<String, Object> variantValueMap = Maps.newHashMap();
				if (entry.getValue() instanceof Map) {
					Map<String, Object> subMap = (Map) entry.getValue();
					String namespace = null;
					String eventName = null;
					for (Entry<String, Object> subentry : subMap.entrySet()) {
						eventName = subentry.getKey().split(" ")[0];
						namespace = subentry.getKey().split(" ")[1];

						// Variantの場合、
						// if (DslProvider.getInstance().getDsl()
						// .getVariantComponent().getVariantMap()
						// .containsKey(subentry.getKey())) {
						//
						// Variant variant = DslProvider.getInstance()
						// .getDsl().getVariantComponent()
						// .getVariantMap().get(subentry.getKey());
						// TypeDef returnType = DslProvider
						// .getInstance()
						// .getDsl()
						// .getDslConfig()
						// .getTypeDefMap()
						// .get(DslProvider.getInstance().getDsl()
						// .getDslConfig().getVariantMap()
						// .get(subentry.getKey()).getType());
						// if (Map.class.isAssignableFrom(variant
						// .getReturnValueClazz())) {
						// if (subentry.getValue() instanceof Map) {
						// // TYPEの場合の型変換
						// Map<String, Object> sub2Map = (Map<String, Object>)
						// subentry
						// .getValue();
						// for (Entry<String, Object> sub2entry : sub2Map
						// .entrySet()) {
						// DataTypeUtils typeutils = new DataTypeUtils(
						// DslProvider.getInstance()
						// .getDsl()
						// .getDslConfig());
						// Object newValue = TypeConverter
						// .convert(sub2entry.getValue())
						// .to(typeutils.getJavaType(returnType
						// .getDetail()
						// .get(sub2entry.getKey())
						// .getType()));
						//
						// sub2Map.put(sub2entry.getKey(),
						// newValue);
						// // System.out.println();
						// }
						//
						// variantValueMap.put(subentry.getKey(),
						// sub2Map);
						// // new Expectations() {
						// // {
						// // context.getVariantCache().put(
						// // subentry.getKey(), sub2Map);
						// // }
						// // };
						// }
						//
						// }
						// } else {

						// チェック実施 subentry.getKey()
						EventDatomDef df = DslProvider
								.getInstance()
								.getDsl()
								.getDslConfigSet()
								.getDslConfigMap()
								.get(namespace)
								.getEventMap()
								.get(namespace + COMMON_DSL.SHARP_SYMBO
										+ eventName);
						// Eventの場合
						if (df != null) {
							if (subentry.getValue() instanceof Map) {
								Map<String, Object> sub2Map = (Map) subentry
										.getValue();
								Event event = new Event();
								event.setDetail(sub2Map);
								// 2018-10-23 13:38:47
								// event.setCreateTime(entry
								// .getKey());
								// 2018/10/23 13:38:47
								event.setCreateTime(TypeConverter.convert(
										entry.getKey()).toLocalDateTime());
								event.setEventName(eventName);
								enents.add((Event) event);
								event.setNamespace(namespace);
							} else {
								// event属性がない場合
								Event event = new Event();
								event.setEventName(namespace
										+ COMMON_DSL.SHARP_SYMBO + eventName);
								event.setNamespace(namespace);

							}
						}
					}

					// System.out.println();
				}

				// Start起動

				// SimpleDateFormat formatter= new
				// SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				ObjectMapper mapper = new ObjectMapper()
						.registerModule(new JavaTimeModule());
				mapper.configure(
						SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
				// mapper.getSerializationConfig().with(formatter);
				// mapper.setTimeZone(TimeZone.getTimeZone("GMT+9"));
				String eventStr = mapper.writeValueAsString(enents);

				LocalDateTime now = LocalDateTime.now().with(TemporalAdjusters
						.lastDayOfYear());

				try {
					Fluxengine.exec(eventStr, now);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// System.out.println();
		}
	}

	private static void registerDsl() {
		DslRegister.main(new String[] { dslFilePath, "1" });
	}

}
