// ********RoostGPT********
// Generated Playwright test using RoostGPT

// ********RoostGPT********
const { test, expect } = require('@playwright/test');

test('UI session recording test', async ({ page, context }) => {

// Navigate to the page
await page.goto('https://app.roost.ai/login');

// Add cookie
await context.addCookies([{"name": "theme", "value": "dark", "domain": ".app.roost.ai", "path": "/", "expires": 1728388335.228085, "size": 9, "httpOnly": false, "secure": true, "session": false, "sameSite": "Strict", "priority": "Medium", "sameParty": false, "sourceScheme": "Secure"}]);

// Validate the full HTML content
const content = await page.content();
expect(content).toContain(`[full HTML content]`);

...
});

