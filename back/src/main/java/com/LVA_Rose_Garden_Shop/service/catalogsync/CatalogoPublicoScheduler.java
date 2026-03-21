package com.LVA_Rose_Garden_Shop.service.catalogsync;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CatalogoPublicoScheduler {

    private final CatalogoPublicoSyncService catalogoPublicoSyncService;

    @Scheduled(cron = "${app.catalog-sync.cron:0 0 3 ? * MON}", zone = "${app.catalog-sync.zone:America/Fortaleza}")
    public void sincronizarSemanalmente() {
        catalogoPublicoSyncService.sincronizarCatalogoPublico();
    }
}
