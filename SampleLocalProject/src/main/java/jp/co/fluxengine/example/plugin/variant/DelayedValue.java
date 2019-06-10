package jp.co.fluxengine.example.plugin.variant;

import jp.co.fluxengine.stateengine.annotation.DslName;
import jp.co.fluxengine.stateengine.annotation.Variant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Variant("rule/パケット積算#遅延ダミー値")
public class DelayedValue {

    private static final Logger LOG = LogManager.getLogger(DelayedValue.class);

    @DslName("get")
    public double get(double usage) {
        LOG.info("DelayedValue start usage = " + usage);
        LOG.info("Current Thread ID = " + Thread.currentThread().getId());
        if (usage <= 100) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOG.info("DelayedValue end usage = " + usage);
        return 0.0;
    }

}
