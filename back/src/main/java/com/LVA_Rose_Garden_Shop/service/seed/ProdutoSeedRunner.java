package com.LVA_Rose_Garden_Shop.service.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoSeedRunner implements CommandLineRunner {

    private final ProdutoSeedService produtoSeedService;

    @Override
    public void run(String... args) {
        produtoSeedService.popularSeVazio();
    }
}
